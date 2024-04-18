package com.pitang.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    @Column(name = "firstName", nullable = false)
    private String firstName;

    @NotEmpty(message = "Missing fields")
    @Column(name = "lastName", nullable = false)
    private String lastName;

    @NotEmpty(message = "Missing fields")
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @NotEmpty(message = "Missing fields")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotEmpty(message = "Missing fields")
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty(message = "Missing fields")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "created_at", updatable = false)
    private Date createdAt = new Date();

    @Column(name = "last_login")
    private Date lastLogin;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<CarModel> cars;
}
