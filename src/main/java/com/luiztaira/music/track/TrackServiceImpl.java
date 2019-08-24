package com.luiztaira.music.track;

import java.util.Optional;

import com.luiztaira.music.RabbitAmqpRunner;
import com.luiztaira.music.track.Track;
import com.luiztaira.music.track.TrackService;
import com.luiztaira.music.utils.exceptions.ResourceNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.luiztaira.music.track.TrackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class TrackServiceImpl implements TrackService {

	@Autowired
	private TrackRepository repository;

	@Override
	public String create(Track track) {
		return repository.save(track).getId();
	}

	@Override
	@Transactional
	public Track get(String id) {
		Optional<Track> optional = repository.findById(id);
		if(optional.isPresent())
			return optional.get();
		throw new ResourceNotFoundException("No track found for id: " + id);
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

	@Override
	@RabbitListener(queues = {RabbitAmqpRunner.FANOUT_QUEUE_TRACKS})
	public void receiveMessageFromQueue(Track track) {
		log.info("Track pop from queue: " + track);
		create(track);
	}

	private Pageable createPageRequest(int page, int size, String orderBy) {
		return PageRequest.of(page, size, Sort.by(((orderBy == null || orderBy == "") ? "id" : orderBy)));
	}
}