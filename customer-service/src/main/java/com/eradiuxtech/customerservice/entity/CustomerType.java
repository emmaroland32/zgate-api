package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "customer_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerType extends CoreEntity {

    @Column(name = "name", unique = true)
    String name;

    @Column(name = "type", unique = true)
    String type;

    @Column(name = "comment")
    String comment;
}
