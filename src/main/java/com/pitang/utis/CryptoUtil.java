package com.pitang.utis;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component("cryptoUtil")
public class CryptoUtil {
	private final Pattern BCRYPT_PATTERN = Pattern.compile("\\A\\$2a?\\$\\d\\d\\$[./0-9A-Za-z]{53}");

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	public CryptoUtil() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
	}

	public String hashPassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

	public boolean matches(String password, String hash) {
		return bCryptPasswordEncoder.matches(password, hash);
	}

	public boolean isNotHashPassword(String password) {
		Matcher matcher = BCRYPT_PATTERN.matcher(password);
		return !matcher.matches();
	}
}
