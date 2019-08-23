package com.luiztaira.sample.music.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.luiztaira.sample.music.domain.Track;

/**
 * Repository for Disc documents. Sample services are extended from {@link MongoRepository}
 * Its not necessary to implement it. Spring Boot do it.
 *
 * Custom query needed to be implemented
 */
public interface TrackRepository extends MongoRepository<Track, String>{

}
