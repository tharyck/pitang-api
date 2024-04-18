package com.pitang.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_car")
@Entity
public class CarModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "year_model", nullable = false)
    private Integer year;
    @NotEmpty
    @Column(name = "licensePlate", nullable = false, unique = true)
    private String licensePlate;

    @NotEmpty
    @Column(name = "model", nullable = false)
    private String model;

    @NotEmpty
    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "used")
    private Integer used = 0;

    @Column(name = "user_id")
    private Long userId;
}