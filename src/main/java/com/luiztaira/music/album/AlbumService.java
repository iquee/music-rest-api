package com.luiztaira.music.album;

import com.luiztaira.music.utils.PopFromQueue;

public interface AlbumService extends PopFromQueue<Album> {

    String create(Album album);

    Album get(String id);

    void receiveMessageFromQueue(Album album);
}
