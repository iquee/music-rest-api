package com.github.iquee.music.album;

import com.github.iquee.music.utils.PopFromQueue;

public interface AlbumService extends PopFromQueue<Album> {

    String create(Album album);

    Album get(String id);

    void receiveMessageFromQueue(Album album);
}
