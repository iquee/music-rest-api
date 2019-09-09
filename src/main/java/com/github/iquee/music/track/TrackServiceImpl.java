package com.github.iquee.music.track;

import java.util.Optional;

import com.github.iquee.music.utils.exceptions.ResourceNotFoundException;
import com.github.iquee.music.RabbitAmqpRunner;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

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
	@RabbitListener(queues = {RabbitAmqpRunner.FANOUT_QUEUE_TRACKS})
	public void receiveMessageFromQueue(Track track) {
		log.info("Track pop from queue: " + track);
		create(track);
	}
}