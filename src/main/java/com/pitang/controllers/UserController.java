package com.pitang.controllers;

import com.pitang.dtos.inputs.UserInput;
import com.pitang.dtos.outputs.UserOutput;
import com.pitang.models.UserModel;
import com.pitang.services.UserService;
import com.pitang.dtos.outputs.ErrorOutput;
import com.pitang.utis.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import java.lang.reflect.Type;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(RestConstants.USER_URI)
@CrossOrigin
@Log4j2
@Api(tags = "User")

public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping
    @ApiOperation(value = "Create a User")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserInput userInput) {
        try {
            UserModel user = modelMapper.map(userInput, UserModel.class);
            return ResponseEntity.ok().body(this.userService.create(user));
        } catch (RuntimeException re) {
            return ResponseEntity.badRequest().body(new ErrorOutput(HttpStatus.BAD_REQUEST.value(), re.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorOutput(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a User")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserInput userInput) {
        try {
            UserModel user = modelMapper.map(userInput, UserModel.class);
            user.setId(id);
            return ResponseEntity.ok(userService.update(user));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "Show a User")
    public ResponseEntity<?> showUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(modelMapper.map(this.userService.show(id), UserOutput.class));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Show All Users")
    public ResponseEntity<?> indexUsers() {
        try {
            Type listType = new TypeToken<List<UserOutput>>(){}.getType();
            List<UserOutput> users = modelMapper.map(this.userService.index(),listType);
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @DeleteMapping({"/{id}"})
    @ApiOperation(value = "Delete a User")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            this.userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}