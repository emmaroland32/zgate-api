package com.eradiuxtech.zgate.complianceservice.entity;


import com.eradiuxtech.zgate.complianceservice.entity.core.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company extends CoreEntity implements Serializable {

    String name;
}
