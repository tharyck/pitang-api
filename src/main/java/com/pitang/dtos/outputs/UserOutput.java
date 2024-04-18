package com.pitang.dtos.outputs;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserOutput {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Date birthday;

    private String login;

    private String phone;

    private Date createdAt;

    private Date lastLogin;

    private List<CarOutput> cars;

    private Integer usedCounter;
}
