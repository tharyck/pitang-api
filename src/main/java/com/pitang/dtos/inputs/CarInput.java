package com.pitang.dtos.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CarInput {

    private Long userId;

    private Integer year;

    private String licensePlate;

    private String model;

    private String color;
}