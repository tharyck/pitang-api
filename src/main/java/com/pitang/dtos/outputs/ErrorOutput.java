package com.pitang.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorOutput {

	private Integer errorCode;
	private String message;
}
