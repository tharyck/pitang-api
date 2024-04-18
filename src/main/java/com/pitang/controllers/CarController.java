package com.pitang.controllers;

import com.pitang.dtos.inputs.CarInput;
import com.pitang.dtos.outputs.CarOutput;
import com.pitang.models.CarModel;
import com.pitang.services.CarService;
import com.pitang.services.LoginService;
import com.pitang.utis.RestConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping(RestConstants.CAR_URI)
@CrossOrigin
@Log4j2
@Api(tags = "Car")
public class CarController {

    private final CarService carService;

    private final LoginService loginService;

    private final ModelMapper modelMapper;

    @Autowired
    public CarController(CarService carService, LoginService loginService) {
        this.carService = carService;
        this.loginService = loginService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping()
    @ApiOperation(value = "Create a Car")
    public ResponseEntity<?> createCar(@Valid @RequestBody CarInput carInput) {
        try {
            Long userId = loginService.getUserByToken().getId();
            carInput.setUserId(userId);
            CarModel carModel = this.carService.create(modelMapper.map(carInput, CarModel.class));
            return ResponseEntity.ok(modelMapper.map(carModel, CarOutput.class));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a Car")
    public ResponseEntity<?> updateCar(@PathVariable("id") Long id, @Valid @RequestBody CarInput carInput) {
        try {
            Long userId = loginService.getUserByToken().getId();
            carInput.setUserId(userId);
            CarModel carModel = this.carService.update(id, modelMapper.map(carInput, CarModel.class));
            return ResponseEntity.ok(modelMapper.map(carModel, CarOutput.class));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping({"/{id}"})
    @ApiOperation(value = "Show a Car")
    public ResponseEntity<?> showCar(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(modelMapper.map(this.carService.show(id), CarOutput.class));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping()
    @ApiOperation(value = "Show All Cars")
    public ResponseEntity<?> indexCars() {
        Long userId = loginService.getUserByToken().getId();
        try {
            Type listType = new TypeToken<List<CarOutput>>(){}.getType();
            List<CarOutput> cars = modelMapper.map(this.carService.index(userId),listType);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @DeleteMapping({"/{id}"})
    @ApiOperation(value = "Delete a Car")
    public ResponseEntity<?> deleteEvaluate(@PathVariable("id") Long id) {
        try {
            this.carService.delete(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
