package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import com.eradiuxtech.customerservice.entity.core.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;

@Entity
@Table(name = "address_types")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressType extends CoreEntity implements Serializable {
    @Column(name = "name", unique = true)
    String name;

    @Column(name = "type", unique = true)
    String type;

    @Column(name = "description")
    String description;

}