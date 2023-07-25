package com.eradiuxtech.zgate.systemservice.entity;


import com.eradiuxtech.zgate.systemservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "smtp")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Smtp extends CoreEntity implements Serializable {

    private   String host;

    private  String port;

    private   String username;

    private  String password;

    private String fromEmail;

    private String fromName;

    private String description;

}
