package com.github.iquee.music.track;

import com.github.iquee.music.album.Album;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "tracks")
public class Track implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @NotNull
    private String name;
    @NotNull
    @DBRef
    private Album album;
    private String spotifyUrl;

    public Track(@NotNull String name, @NotNull Album album, String spotifyUrl) {
        this.name = name;
        this.album = album;
        this.spotifyUrl = spotifyUrl;
    }
}
