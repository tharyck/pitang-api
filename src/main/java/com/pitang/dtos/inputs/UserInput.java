package com.pitang.dtos.inputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInput {

    private String firstName;

    private String lastName;

    private String email;

    private Date birthday;

    private String login;

    private String password;

    private String phone;

    private List<CarInput> cars;
}