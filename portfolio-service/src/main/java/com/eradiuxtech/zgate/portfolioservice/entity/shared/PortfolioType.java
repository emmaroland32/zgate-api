package com.eradiuxtech.zgate.portfolioservice.entity.shared;

import com.eradiuxtech.zgate.portfolioservice.entity.core.CoreEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "portfolio_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioType extends CoreEntity {
    @Column(name = "code", nullable = false)
    String code;

    @Column(name = "name", nullable = false)
    String name;

}
