package com.eradiuxtech.zgate.complianceservice.entity.core;

import com.eradiuxtech.zgate.complianceservice.convertor.StatusConverter;
import com.eradiuxtech.zgate.complianceservice.util.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@Setter
public class Reject extends BaseEntity {

    @Column(name = "rejected_by")
    protected String rejectedBy;

    @Column(name = "rejected_at")
    protected LocalDateTime rejectedAt;

    @Column(name = "rejection_note")
    protected String rejectionNote;

    @Column(name = "is_reject", columnDefinition = "boolean default false", nullable = false)
    protected Boolean isRejected = false;

    @Column(name = "rejection_stage")
    @Convert(converter = StatusConverter.class)
    protected Status rejectionStage;

    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter.class)
    protected Status status = Status.PENDING;



    @PreUpdate
    private void RejectionPrePersist() {
        if(rejectionNote == null && rejectedBy != null && rejectedAt != null){
            rejectedBy = "Reject by " + rejectedBy + " at " + rejectedAt;
        }
        if(rejectedBy != null && rejectedAt != null){
            isRejected = true;
        }
    }
}
