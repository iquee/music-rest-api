package com.github.iquee.music.artist;

import com.github.iquee.music.utils.PopFromQueue;

public interface ArtistService extends PopFromQueue<Artist> {

    String create(Artist artist);

    Artist get(String id);

    void receiveMessageFromQueue(Artist artist);
}
