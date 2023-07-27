package com.eradiuxtech.customerservice.entity;


import com.eradiuxtech.customerservice.entity.core.CoreEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "customer_social_media")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerSocialMedia extends CoreEntity implements Serializable {

    @ManyToOne(optional = false)
    private SocialMediaType socialMediaType;

    @Column(name = "description")
    private String description;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ucid", referencedColumnName = "ucid")
    private Customer customer;

}
