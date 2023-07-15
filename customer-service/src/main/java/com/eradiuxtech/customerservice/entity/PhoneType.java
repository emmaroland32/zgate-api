package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "phone_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PhoneType extends CoreEntity implements Serializable {
    @Column(name = "name", unique = true)
    String name;

    @Column(name = "type", unique = true)
    String type;

    @Column(name = "comment")
    String comment;
}
