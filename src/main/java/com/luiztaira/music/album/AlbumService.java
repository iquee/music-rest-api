package com.luiztaira.music.album;

import org.springframework.data.domain.Page;

public interface AlbumService {

    String create(Album album);

    Album get(String id);

    void delete(String id);

    void update(Album track);

    Page<Album> list(int page, int size, String orderBy);

    Page<Album> findByNameOrArtist(String pattern, int page, int size, String orderBy);

    void receiveMessageFromQueue(Album album);
}
