package com.pitang.repositories;

import com.pitang.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {
    @Override
    @NotNull
    CarModel getById(@NotNull Long aLong);

}