package com.pitang.dtos.inputs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class CarInput {

    @ApiModelProperty(example = "1")
    private Long id;

    @ApiModelProperty(example = "1")
    private Long userId;

    @ApiModelProperty(example = "2024", required = true)
    private Integer year;

    @ApiModelProperty(example = "QSJ-2228", required = true)
    private String licensePlate;

    @ApiModelProperty(example = "VW", required = true)
    private String model;

    @ApiModelProperty(example = "Cinza", required = true)
    private String color;
}