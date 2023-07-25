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
@Table(name = "portfolio_property")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioProperty extends CoreEntity {

    @Column(name = "portfolio_property_id", nullable = false)
    String portfolioPropertyId;

    @Column(name = "portfolio_id", nullable = false)
    String portfolioId;
}
