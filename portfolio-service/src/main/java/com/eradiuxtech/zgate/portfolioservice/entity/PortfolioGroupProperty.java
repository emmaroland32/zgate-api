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
@Table(name = "portfolio_group_property")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioGroupProperty extends CoreEntity {

    @Column(name = "portfolio_group_property_id", nullable = false)
    String portfolioGroupPropertyId;

    @Column(name = "portfolio_group_id", nullable = false)
    String portfolioGroupId;
}
