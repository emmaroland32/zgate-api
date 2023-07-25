package com.programming.eradiuxtech.entity;


import com.programming.eradiuxtech.entity.core.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "sms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sms extends CoreEntity implements Serializable {

    String name;

}