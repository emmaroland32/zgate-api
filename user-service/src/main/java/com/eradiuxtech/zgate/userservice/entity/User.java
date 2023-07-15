package com.eradiuxtech.zgate.userservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Email
    String email;
}
