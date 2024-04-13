package com.pitang.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CarOutput {

    private Integer year;

    private String licensePlate;

    private String model;

    private String color;

    private Long userId;
}
