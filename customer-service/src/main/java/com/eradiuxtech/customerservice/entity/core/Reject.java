package com.eradiuxtech.customerservice.entity.core;

import com.eradiuxtech.customerservice.convertor.StatusConverter;
import com.eradiuxtech.customerservice.exception.CustomNotFoundException;
import com.eradiuxtech.customerservice.security.AuthenticationFacade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@Setter
public class Reject extends BaseEntity {

    @Column(name = "rejected_by")
    private String rejectedBy;

    @Column(name = "rejected_at")
    private LocalDateTime rejectedAt;

    @Column(name = "rejection_note")
    private String rejectionNote;

    @Column(name = "rejection_stage")
    @Convert(converter = StatusConverter.class)
    private Status rejectionStage;

    @Column(name = "status", nullable = false)
    @Convert(converter = StatusConverter.class)
    protected Status status = Status.PENDING;

    public void retrieveFromRejection() {
        if(status == Status.REJECTED){
            status = rejectionStage;
            rejectionNote = null;
            rejectedBy = null;
            rejectedAt = null;
        }
    }

    public void reject() {
        if(status == Status.ACTIVE){
            throw new CustomNotFoundException("Entity already approved", this.getId());
        }
        if(status == Status.REJECTED){
            throw new CustomNotFoundException("Entity already rejected", this.getId());
        }
        if(status == Status.PENDING_APPROVAL || status == Status.PENDING_REVIEW){
           status = Status.REJECTED;
        }
    }

    @PreUpdate
    private void preUpdate() {
        if (status == Status.REJECTED) {
            rejectionStage = status;
        }
    }

    @PostUpdate
    private void postUpdate() {
        if(rejectionNote == null && rejectedBy != null && rejectedAt != null && status == Status.REJECTED){
            rejectedBy = new AuthenticationFacade().getAuthentication().getName();
            rejectedAt = LocalDateTime.now();
            rejectionNote = "Reject by " + rejectedBy + " at " + rejectedAt;
        }
    }
}
