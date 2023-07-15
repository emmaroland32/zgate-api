package com.eradiuxtech.zgate.portfolioservice.entity;


import com.eradiuxtech.zgate.portfolioservice.entity.core.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import java.io.Serializable;


@Entity
@Table(name = "portfolio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = true)
//@SQLDelete(sql = "UPDATE customers SET deleted = true WHERE id=?")
//@Filter(name = "deletedCustomerFilter", condition = "deleted = :isDeleted")
//@FilterDef(name = "deletedCustomerFilter", parameters = @ParamDef(name = "isDeleted", type = org.hibernate.type.descriptor.java.BooleanJavaType.class))
public class Portfolio extends Review implements Serializable {

    @Column(name = "portfolio_id", nullable = false)
    protected String portfolioId;

    @Column(name = "portfolio_group_id", nullable = false)
    protected String portfolioGroupId;

    @Column(name = "unitized", nullable = false, columnDefinition = "boolean default false")
    protected Boolean unitized;

    @Column(name = "portfolio_type", nullable = false)
    protected String portfolioType;

    @Column(name = "ucid", updatable = false, nullable = false, unique = true)
    protected Long ucid;

    @Column(name = "note")
    protected String note;

//    @PrePersist
//    private void PrePersist() {
//        if(note == null){
//            note = "Portfolio Created by " + createdBy + " at " + createdAt;
//        }
//    }

}
