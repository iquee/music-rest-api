package com.luiztaira.sample.music.changelog;

import com.github.javafaker.Faker;
import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangelog {

    private static final String clientId = "e42ee3af925745ebaa0bdb13b34cd182";
    private static final String clientSecret = "3520022d8d254c6daf7e20d57b131a11";    

    @ChangeSet(order = "001", id = "spotifyAlbums", author = "luiz.taira")
    public void requestSpotifyAlbums(DB db) throws JSONException, SpotifyWebApiException, IOException {
        DBCollection discCollection = db.getCollectionFromString("tracks");

        // get spotify access token
        SpotifyApi spotifyApi = new SpotifyApi.Builder().setClientId(clientId).setClientSecret(clientSecret).build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        String accessToken = clientCredentialsRequest.execute().getAccessToken();

        // request rock tracks


        URL url = new URL("https://api.spotify.com/v1/search?q=rock&type=track&limit=50&offset=0&popularity=100&market=BR");
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

        List<Document> musics = new ArrayList<>();

        // response
        JSONObject obj = new JSONObject(response.toString());
        JSONObject tracks = obj.getJSONObject("tracks");
        JSONArray list = tracks.getJSONArray("items");
        while (list.iterator().hasNext()) {
            JSONObject singleTrack = (JSONObject) list.iterator().next();
            Document track = new Document();
            track.append("name", singleTrack.get("name"));
            track.append("artists", singleTrack.getJSONArray("artists").toList());

            JSONObject albumObj = singleTrack.getJSONObject("album");
            Document album = new Document();
            album.put("name", albumObj.get("name"));
            album.put("spotify_album_id", albumObj.get("id"));
            album.put("release_date", albumObj.get("release_date"));
            album.put("total_tracks", albumObj.get("total_tracks"));
            album.put("artists", albumObj.getJSONArray("artists").toList());
            album.put("images", albumObj.getJSONArray("images").toList());
            track.append("album", album);

            track.append("popularity", singleTrack.get("popularity"));
            track.append("duration_ms", singleTrack.get("duration_ms"));
            track.append("spotify_id", singleTrack.get("id"));
            track.append("spotify", singleTrack.getJSONObject("external_urls").get("spotify"));

            musics.add(track);
        }

        // insert in mongodb
        discCollection.insert(musics);

    }
}