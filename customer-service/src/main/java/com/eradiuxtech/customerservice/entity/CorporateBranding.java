package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "corporate_branding")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CorporateBranding extends CoreEntity implements Serializable {
    private String logo;
    private String banner;
    private String slogan;
    private String website;
    private String primaryColor;
    private String secondaryColor;

}
