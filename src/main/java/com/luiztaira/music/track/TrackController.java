package com.luiztaira.music.track;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tracks")
public class TrackController{

	@Autowired
	private TrackService trackService;

	@ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity<Track> get(@PathVariable("id") String id){
		return new ResponseEntity<>(trackService.get(id), HttpStatus.OK);
	}

}