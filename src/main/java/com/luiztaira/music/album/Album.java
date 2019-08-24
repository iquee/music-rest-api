package com.luiztaira.music.album;

import com.luiztaira.music.artist.Artist;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "albums")
public class Album implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @NotNull
    private String name;
    @NotNull
    @DBRef
    private List<Artist> artists;
    private String spotifyUrl;

    public Album(@NotNull String name, @NotNull List<Artist> artists, String spotifyUrl) {
        this.name = name;
        this.artists = artists;
        this.spotifyUrl = spotifyUrl;
    }
}
