package com.eradiuxtech.zgate.portfolioservice.entity.core;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity extends CoreEntity {

    @Column(name = "deleted", columnDefinition = "boolean default false")
    protected Boolean deleted = false;

    @Column(name = "deleted_at")
    protected Date deletedAt;

}
