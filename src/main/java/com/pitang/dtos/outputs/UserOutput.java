package com.pitang.dtos.outputs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserOutput {

    private Long userId;

    private String firstName;

    private String lastName;

    private String email;

    private Date birthday;

    private String login;

    private String password;

    private String phone;

}
