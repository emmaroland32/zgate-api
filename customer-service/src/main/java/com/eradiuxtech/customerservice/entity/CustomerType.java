package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "customer_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerType extends CoreEntity implements Serializable {

    @Column(name = "name", unique = true)
    String name;

    @Column(name = "type", unique = true)
    String type;

    @Column(name = "comment")
    String comment;
}
