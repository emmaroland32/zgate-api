package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "signatory_classes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignatoryClass extends CoreEntity implements Serializable {

    @Column(name = "type", unique = true)
    Character type;

    @Column(name = "name", unique = true)
    String name;

    @Column(name = "comment")
    String comment;
}