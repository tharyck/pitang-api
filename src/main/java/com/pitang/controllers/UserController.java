package com.pitang.controllers;

import com.pitang.dtos.inputs.UserInput;
import com.pitang.dtos.outputs.UserOutput;
import com.pitang.models.UserModel;
import com.pitang.services.UserService;
import com.pitang.utis.ErrorsException;
import com.pitang.utis.RestConstants;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RestConstants.USER_URI)
@CrossOrigin
@Log4j2
public class UserController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    public UserController(UserService userService) {
        this.userService = userService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserInput userInput) {
        try {
            UserModel user = modelMapper.map(userInput, UserModel.class);
            return ResponseEntity.ok().body(this.userService.create(user));
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @Valid @RequestBody UserInput userInput) {
        try {
            UserModel user = modelMapper.map(userInput, UserModel.class);
            user.setId(id);
            return ResponseEntity.ok(userService.update(user));
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> showUser(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(modelMapper.map(this.userService.show(id), UserOutput.class));
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping()
    public ResponseEntity<?> indexUsers() {
        try {
            return ResponseEntity.ok(modelMapper.map(this.userService.index(), List.class));
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteEvaluate(@PathVariable("id") Long id) {
        try {
            this.userService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
