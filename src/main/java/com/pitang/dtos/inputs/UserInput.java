package com.pitang.dtos.inputs;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

import  com.fasterxml.jackson.annotation.JsonInclude ;
import  com.fasterxml.jackson.annotation.JsonInclude.Include ;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInput {

    @ApiModelProperty(example = "Hello", required = true)
    private String firstName;

    @ApiModelProperty(example = "World", required = true)
    private String lastName;

    @ApiModelProperty(example = "hello@world.com", required = true)
    private String email;

    @ApiModelProperty(example = "1990-05-01", required = true)
    private Date birthday;

    @ApiModelProperty(example = "Hello.Word", required = true)
    private String login;

    @ApiModelProperty(example = "P@ssw0rd", required = true)
    private String password;

    @ApiModelProperty(example = "988888888", required = true)
    private String phone;

    private List<CarInput> cars;
}