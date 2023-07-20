package com.eradiuxtech.zgate.auditservice.entity;


import com.eradiuxtech.zgate.auditservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User extends CoreEntity implements Serializable {


    String email;


}
