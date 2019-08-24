package com.luiztaira.music.artist;

import com.luiztaira.music.RabbitAmqpRunner;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ArtistServiceImpl implements ArtistService{

    @Autowired
    ArtistRepository artistRepository;

    @Override
    public String create(Artist artist) {
        try {
            return artistRepository.save(artist).getId();
        } catch (Exception e){
            log.error("Error in create artists", e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public Artist get(String id) {
        Optional<Artist> optional = artistRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public void delete(String id) {
    }

    @Override
    public void update(Artist track) {
    }

    @Override
    public Page<Artist> list(int page, int size, String orderBy) {
        return null;
    }

    @Override
    public Page<Artist> findByNameOrArtist(String pattern, int page, int size, String orderBy) {
        return null;
    }

    @Override
    @RabbitListener(queues = {RabbitAmqpRunner.FANOUT_QUEUE_ARTISTS})
    public void receiveMessageFromQueue(Artist artist) {
        log.info("Artist pop from queue: " + artist);
        create(artist);
    }
}