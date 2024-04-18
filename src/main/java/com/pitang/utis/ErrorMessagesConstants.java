package com.pitang.utis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorMessagesConstants implements Serializable {
	LOGIN_NOT_VALID_USER_PASSWORD("Invalid login or password", "Invalid login or password"),
	UK_LOGIN("Login already exists", "UK_LOGIN"),
	UK_EMAIL("Email already exists", "UK_EMAIL"),
	UK_PLATE("License plate already exists", "UK_PLATE"),
	NULL("Missing fields", "[null]");

	private String message;
	private String error;

}
