package com.github.iquee.music.artist;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Document(collection = "artists")
public class Artist implements Serializable {

    @Id
    @EqualsAndHashCode.Include
    private String id;
    @NotNull
    @Indexed(unique = true)
    private String name;
    private String spotifyUrl;

    public Artist(@NotNull String name, String spotifyUrl) {
        this.name = name;
        this.spotifyUrl = spotifyUrl;
    }
}
