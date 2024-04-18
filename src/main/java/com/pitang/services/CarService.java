package com.pitang.services;

import com.pitang.models.CarModel;
import com.pitang.repositories.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
        CarModel carModel = this.carRepository.getById(id);
        carModel.setUsed(carModel.getUsed()+1);
        return this.carRepository.save(carModel);
    }

    public List<CarModel> index(Long userId) {
        return this.carRepository.findAllByUserIdOrderByUsedDesc(userId).stream().toList();
    }

    @Transactional
    public void delete(Long id) {
        this.carRepository.deleteById(id);
    }
}
