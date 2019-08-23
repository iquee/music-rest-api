package com.luiztaira.sample.music.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TrackException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TrackException(String message, Throwable cause){
		super(message, cause);
	}

}
