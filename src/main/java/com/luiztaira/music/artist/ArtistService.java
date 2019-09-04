package com.luiztaira.music.artist;

import com.luiztaira.music.utils.PopFromQueue;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArtistService extends PopFromQueue<Artist> {

    String create(Artist artist);

    Artist get(String id);

    void receiveMessageFromQueue(Artist artist);
}
