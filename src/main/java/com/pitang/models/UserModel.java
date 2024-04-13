package com.pitang.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
@Entity
public class UserModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotEmpty
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotEmpty
    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @NotEmpty
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "car_id")
    private List<CarModel> cars;
}
