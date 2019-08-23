package com.luiztaira.sample.music.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.luiztaira.sample.music.domain.Track;
import com.luiztaira.sample.music.exception.TrackException;

/**
 * Service to manage Discs collection
 */
@Service
public interface TrackService {

	String create(Track track);

	Track get(String id);

	void delete(String id);

	void update(Track track);

	Page<Track> list(int page, int size, String orderBy);

	Page<Track> findByNameOrArtist(String pattern, int page, int size, String orderBy);
}
