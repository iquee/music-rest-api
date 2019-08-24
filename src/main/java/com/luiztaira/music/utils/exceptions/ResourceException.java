package com.luiztaira.music.utils.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResourceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResourceException(String message, Throwable cause){
		super(message, cause);
	}

}
