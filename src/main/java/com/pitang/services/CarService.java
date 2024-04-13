package com.pitang.services;

import com.pitang.models.CarModel;
import com.pitang.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CarService {

    @Autowired
    private CarRepository carRepository;


    public CarModel create(CarModel carModel) {
        return this.carRepository.save(carModel);
    }

    public CarModel update(Long id, CarModel carModel) {
        return this.carRepository.save(carModel);
    }

    public CarModel show(Long id) {
        return this.carRepository.findById(id).get();
    }

    public List<CarModel> index() {
        return this.carRepository.findAll().stream().toList();
    }

    @Transactional
    public void delete(Long id) {
        this.carRepository.deleteById(id);
    }
}
