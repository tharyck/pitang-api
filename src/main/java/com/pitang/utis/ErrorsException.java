package com.pitang.utis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorsException extends RuntimeException {

	private Integer errorCode;

	public ErrorsException(String message, Integer errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
