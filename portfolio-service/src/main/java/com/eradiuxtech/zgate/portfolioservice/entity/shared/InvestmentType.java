package com.eradiuxtech.zgate.portfolioservice.entity.shared;


import com.eradiuxtech.zgate.portfolioservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "investment_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentType extends CoreEntity {

    @Column(name = "name")
    String name;

    @Column(name = "code")
    String code;


}
