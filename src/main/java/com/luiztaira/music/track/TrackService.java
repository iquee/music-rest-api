package com.luiztaira.music.track;

import org.springframework.data.domain.Page;

/**
 * Services to manage Tracks collection
 */
public interface TrackService {

	String create(Track track);

	Track get(String id);

	void delete(String id);

	void update(Track track);

	Page<Track> list(int page, int size, String orderBy);

	Page<Track> findByNameOrArtist(String pattern, int page, int size, String orderBy);

	void receiveMessageFromQueue(Track track);
}
