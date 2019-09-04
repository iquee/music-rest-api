package com.luiztaira.music.album;

import com.luiztaira.music.RabbitAmqpRunner;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public String create(Album album) {
        try {
            return albumRepository.save(album).getId();
        } catch (Exception e){
            log.error("Error in create album", e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public Album get(String id) {
        Optional<Album> optional = albumRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    @RabbitListener(queues = {RabbitAmqpRunner.FANOUT_QUEUE_ALBUMS})
    public void receiveMessageFromQueue(Album album) {
        log.info("Album pop from queue: " + album);
        create(album);
    }
}
