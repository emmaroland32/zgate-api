package com.eradiuxtech.zgate.portfolioservice.entity;

import com.eradiuxtech.zgate.portfolioservice.entity.core.CoreEntity;
import jakarta.persistence.Column;

public class PortfolioGroupClassification extends CoreEntity {

    @Column(name = "portfolio_group_classification_id")
    Long portfolioGroupClassificationId;


}
