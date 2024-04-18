package com.pitang.controllers;

import com.pitang.dtos.inputs.LoginInput;
import com.pitang.dtos.outputs.ErrorOutput;
import com.pitang.dtos.outputs.UserOutput;
import com.pitang.models.UserModel;
import com.pitang.services.LoginService;
import com.pitang.utis.RestConstants;
import com.pitang.utis.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Log4j2
@Api(tags = "Login")
public class LoginController {

    private final LoginService loginService;

    private final ModelMapper modelMapper;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping(RestConstants.LOGIN_URI)
    @ApiOperation(value = "Login a User")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginInput loginInput) {
        try {
            UserModel user = loginService.login(loginInput.getLogin(), loginInput.getPassword());
            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            UserOutput userOutput = modelMapper.map(user, UserOutput.class);
            log.info("user " + user.getId() + " logged");
            HttpHeaders headers = new HttpHeaders();
            headers.add(SecurityUtils.TOKEN_HEADER, loginService.generateToken(userOutput));
            return ResponseEntity.ok().headers(headers).body(userOutput);
        } catch (RuntimeException re) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorOutput(HttpStatus.UNAUTHORIZED.value(), re.getMessage()));
        }
    }
    @PostMapping(RestConstants.ME_URI)
    @ApiOperation(value = "Show Logged User")
    public ResponseEntity<?> autenticatedUser() {
        UserModel user = loginService.getUserByToken();
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        UserOutput userOutput = modelMapper.map(user, UserOutput.class);
        return ResponseEntity.ok().body(userOutput);
    }
}