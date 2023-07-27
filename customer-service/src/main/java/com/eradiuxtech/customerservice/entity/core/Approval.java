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
public class Approval extends Reject {

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "approval_note")
    private String approvalNote;

    @Column(name = "is_approved", columnDefinition = "boolean default false", nullable = false)
    private Boolean isApproved = false;


    @PreUpdate
    private void ApprovePreUpdate() {
        if(approvalNote == null && approvedBy != null && approvedAt != null && forApproval){
            approvalNote = "Customer approved by " + approvedBy + " at " + approvedAt;
        }
        if(approvedBy != null && approvedAt != null && status == Status.FOR_APPROVAL){
            isApproved = true;
        }
    }
}
