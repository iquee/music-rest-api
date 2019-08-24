package com.luiztaira.music.artist;

import org.springframework.data.domain.Page;

import java.util.List;

public interface ArtistService {

    String create(Artist artist);

    Artist get(String id);

    void delete(String id);

    void update(Artist track);

    Page<Artist> list(int page, int size, String orderBy);

    Page<Artist> findByNameOrArtist(String pattern, int page, int size, String orderBy);

    void receiveMessageFromQueue(Artist artist);
}
