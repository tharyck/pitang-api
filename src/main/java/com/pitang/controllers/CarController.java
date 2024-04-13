package com.pitang.controllers;

import com.pitang.dtos.inputs.CarInput;
import com.pitang.dtos.outputs.CarOutput;
import com.pitang.models.CarModel;
import com.pitang.services.CarService;
import com.pitang.utis.ErrorsException;
import com.pitang.utis.RestConstants;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RestConstants.CAR_URI)
@CrossOrigin
@Log4j2
public class CarController {

    private final CarService carService;

    private final ModelMapper modelMapper;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
        this.modelMapper = new ModelMapper();
    }

    @PostMapping()
    public ResponseEntity<?> createCar(@Valid @RequestBody CarInput carInput) {
        try {
            CarModel carModel = this.carService.create(modelMapper.map(carInput, CarModel.class));
            return ResponseEntity.ok(modelMapper.map(carModel, CarOutput.class));
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@PathVariable("id") Long id, @Valid @RequestBody CarInput carInput) {
        try {
            CarModel carModel = this.carService.update(id, modelMapper.map(carInput, CarModel.class));
            return ResponseEntity.ok(modelMapper.map(carModel, CarOutput.class));
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<?> showCar(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(modelMapper.map(this.carService.show(id), CarOutput.class));
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @GetMapping()
    public ResponseEntity<?> indexCars() {
        try {
            return ResponseEntity.ok(modelMapper.map(this.carService.index(), List.class));
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<?> deleteEvaluate(@PathVariable("id") Long id) {
        try {
            this.carService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ErrorsException ee) {
            return ResponseEntity.badRequest().body(ee);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }
}
