package com.eradiuxtech.zgate.portfolioservice.entity;


import com.eradiuxtech.zgate.portfolioservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "portfolio_group")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioGroup extends CoreEntity {

    @Column(name = "portfolio_group_id")
    Long portfolioGroupId;

    @Column(name = "portfolio_group_code")
    Long portfolioGroupCode;

    @Column(name = "name")
    String portfolioGroupName;

    @Column(name = "description")
    String description;

    @Column(name = "portfolio_id")
    Long portfolioId;
}
