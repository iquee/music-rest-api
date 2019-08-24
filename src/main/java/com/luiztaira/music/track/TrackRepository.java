package com.luiztaira.music.track;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for Disc documents. Sample services are extended from {@link MongoRepository}
 * Its not necessary to implement it. Spring Boot do it.
 *
 * Custom query needed to be implemented
 */
public interface TrackRepository extends MongoRepository<Track, String>{

}
