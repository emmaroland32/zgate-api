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
@Table(name = "portfolio_asset_valuation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioAssetValuation extends CoreEntity {

    @Column(name = "portfolio_asset_valuation_id")
    Long portfolioValuationId;

    @Column(name = "portfolio_id")
    Long portfolioId;

}
