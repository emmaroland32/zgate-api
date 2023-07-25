package com.eradiuxtech.customerservice.entity.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "createdBy","modifiedAt", "modifiedBy"},
        allowGetters = true
)
@Getter
@Setter
public class Auditable {

    @CreatedDate
    @Column(name = "created_at",
            updatable = false
           )
    @ColumnDefault("CURRENT_TIMESTAMP")
    protected Date createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    protected LocalDateTime modifiedAt;

    @CreatedBy
    @Column(name = "created_by", columnDefinition = "varchar(255) default 'system'")
    protected String createdBy;

    @LastModifiedBy
    @Column(name = "modified_by")
    protected String modifiedBy;
}
