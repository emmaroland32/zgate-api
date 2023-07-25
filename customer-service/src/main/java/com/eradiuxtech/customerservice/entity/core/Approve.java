package com.eradiuxtech.customerservice.entity.core;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@MappedSuperclass
@Getter
@Setter
public class Approve extends Reject {

    @Column(name = "approved_by")
    protected String approvedBy;

    @Column(name = "approved_at")
    protected LocalDateTime approvedAt;

    @Column(name = "approve_note")
    protected String approveNote;

    @Column(name = "is_approved", columnDefinition = "boolean default false", nullable = false)
    protected Boolean isApproved = false;

    @Column(name = "for_approval", columnDefinition = "boolean default false", nullable = false)
    protected Boolean forApproval= false;

    private void pushForReview() {
        if(!forApproval){
            forApproval = true;
        }
    }
    @PreUpdate
    private void ApprovePrePersist() {
        if(approveNote == null && approvedBy != null && approvedAt != null && forApproval){
            approveNote = "Customer approved by " + approvedBy + " at " + approvedAt;
        }
        if(approvedBy != null && approvedAt != null && forApproval){
            isApproved = true;
        }
    }
}
