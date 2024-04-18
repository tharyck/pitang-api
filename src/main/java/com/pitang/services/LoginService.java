package com.pitang.services;

import com.google.gson.Gson;
import com.pitang.dtos.outputs.UserOutput;
import com.pitang.models.UserModel;
import com.pitang.utis.CryptoUtil;
import com.pitang.utis.ErrorMessagesConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Log4j2
public class LoginService {

    @Value("${login.expiration}")
    public long EXPIRATION_TOKEN;

    @Value("${secret.api}")
    private String SECRET;

    private static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER = "Authorization";

    private final CryptoUtil cryptoUtil;
    private final UserService userService;

    @Autowired
    public LoginService(CryptoUtil cryptoUtil, UserService userService) {
        this.cryptoUtil = cryptoUtil;
        this.userService = userService;
    }

    public UserModel login(String login, String credentials) {
        UserModel user = userService.findByLogin(login);
        if (user == null || !cryptoUtil.matches(credentials, user.getPassword())) {
            throw new RuntimeException(ErrorMessagesConstants.LOGIN_NOT_VALID_USER_PASSWORD.getMessage());
        }

        user.setLastLogin(new Date());
        this.userService.update(user);
        user.setPassword(null);
        return user;
    }

    public final String generateToken(final UserOutput userOutput) {
        return Jwts
                .builder()
                .setSubject(new Gson().toJson(userOutput))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(SECRET.getBytes()))
                .compact();
    }

    public UserModel getUserByToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> map = (Map<String, Object>) auth.getCredentials();
        return this.userService.show((Long) map.get("userId"));
    }

    public Authentication getAuthentication(final HttpServletRequest request) {
        String token = request.getHeader(HEADER);
        if (token != null) {
            token = token.replace(TOKEN_PREFIX, "").trim();
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(SECRET.getBytes()))
                    .parseClaimsJws(token)
                    .getBody();
            String user = claims.getSubject();

            UserOutput userDetails = new Gson().fromJson(user, UserOutput.class);
            log.info("trying connect user " + userDetails.getEmail());
            log.info("user connected " + userDetails.getEmail());
            Map<String, Object> map = new HashMap<>();
            map.put("userId", userDetails.getId());

            return new UsernamePasswordAuthenticationToken(
                    userDetails.getEmail(),
                    map, new HashSet<>());
        } else {
            return null;
        }
    }
}
