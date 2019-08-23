package com.luiztaira.sample.music.service.impl;

import java.util.List;
import java.util.Optional;

import com.luiztaira.sample.music.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.luiztaira.sample.music.domain.Track;
import com.luiztaira.sample.music.exception.TrackException;
import com.luiztaira.sample.music.repository.TrackRepository;

@Component
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackRepository repository;

	@Override
	public String create(Track track) {
		return null;
	}

	@Override
	public Track get(String id) {
		return null;
	}

	@Override
	public void delete(String id) {
	}

	@Override
	public void update(Track track) {
	}

	@Override
	public Page<Track> list(int page, int size, String orderBy) {
		return null;
	}

	@Override
	public Page<Track> findByNameOrArtist(String pattern, int page, int size, String orderBy) {
		return null;
	}

	private Pageable createPageRequest(int page, int size, String orderBy) {
		return PageRequest.of(page, size, Sort.by(((orderBy == null || orderBy == "") ? "id" : orderBy)));
	}
}