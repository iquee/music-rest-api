package com.github.iquee.music;

import com.github.iquee.music.album.Album;
import com.github.iquee.music.track.Track;
import com.github.iquee.music.track.TrackService;
import com.github.iquee.music.artist.Artist;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Configuration
@Order(2)
public class SpotifyLoadData implements CommandLineRunner {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;
    @Value("${spring.data.mongodb.port}")
    private String mongoPort;
    @Value("${spring.data.mongodb.database}")
    private String mongoSchema;
    @Value("${spring.data.mongodb.collection}")
    private String mongoCollection;

    @Autowired
    private TrackService trackService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... arg0) throws Exception {
        Mongo mongo = new Mongo(mongoHost, Integer.parseInt(mongoPort));
        DB db = mongo.getDB(mongoSchema);
        if(db.getCollection(mongoCollection).getCount()  == 0){
            SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId("e42ee3af925745ebaa0bdb13b34cd182").setClientSecret("3520022d8d254c6daf7e20d57b131a11").build();
            ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
            String accessToken = clientCredentialsRequest.execute().getAccessToken();

            // request rock tracks. Spotify search limit allowed is 50
            log.info("Spotify API Request: Search most popular Rock musics in Brazil");
            URL url = new URL("https://api.spotify.com/v1/search?q=genre:rock&type=track&limit=50&offset=0&popularity=100&market=BR");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String output;
            StringBuffer response = new StringBuffer();
            while ((output = in.readLine()) != null) {
                response.append(output);
            }
            in.close();

            // response
            JSONObject obj = new JSONObject(response.toString());
            JSONObject tracks = obj.getJSONObject("tracks");
            JSONArray list = tracks.getJSONArray("items");
            for (int i = 0; i < list.length(); i++) {
                JSONObject singleTrack = (JSONObject) list.get(i);
                List<Artist> artists = new ArrayList<>();
                JSONArray artistsJson = singleTrack.getJSONArray("artists");
                for (int j = 0; j < artistsJson.length(); j++) {
                    JSONObject artist = (JSONObject) artistsJson.get(j);
                    Artist musicArtist = new Artist(artist.getString("id"), artist.get("name").toString(), artist.getJSONObject("external_urls").get("spotify").toString());
                    artists.add(musicArtist);
                    rabbitTemplate.convertAndSend("", RabbitAmqpRunner.FANOUT_QUEUE_ARTISTS, musicArtist);
                }
                JSONObject albumJson = singleTrack.getJSONObject("album");
                Album album = new Album(albumJson.getString("id"), albumJson.get("name").toString(), artists, albumJson.getJSONObject("external_urls").get("spotify").toString());
                rabbitTemplate.convertAndSend("", RabbitAmqpRunner.FANOUT_QUEUE_ALBUMS, album);

                Track track = new Track(singleTrack.get("name").toString(), album, singleTrack.getJSONObject("external_urls").get("spotify").toString());
                rabbitTemplate.convertAndSend("", RabbitAmqpRunner.FANOUT_QUEUE_TRACKS, track);
            }
        }
    }
}