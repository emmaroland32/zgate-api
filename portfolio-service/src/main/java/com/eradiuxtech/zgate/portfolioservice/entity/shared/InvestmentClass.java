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
@Table(name = "investment_class")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvestmentClass extends CoreEntity {

    @Column(name = "name")
    String name;

    @Column(name = "code")
    String code;
}
