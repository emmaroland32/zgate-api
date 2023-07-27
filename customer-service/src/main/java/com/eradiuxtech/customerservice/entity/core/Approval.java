package com.eradiuxtech.customerservice.entity.core;

import com.eradiuxtech.customerservice.exception.CustomNotFoundException;
import com.eradiuxtech.customerservice.security.AuthenticationFacade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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


    public void  forApproval() {
        if(status == Status.ACTIVE){
            throw new CustomNotFoundException("Entity already approved", this.getId());
        }
        if(status == Status.REJECTED){
            throw new CustomNotFoundException("Entity already rejected", this.getId());
        }
        if(status == Status.REVIEWED){
            status = Status.PENDING_APPROVAL;
        }
    }

    public void approve() {
        if(status == Status.ACTIVE){
            throw new CustomNotFoundException("Entity already approved", this.getId());
        }
        if(status == Status.REJECTED){
            throw new CustomNotFoundException("Entity already rejected", this.getId());
        }
        if(status == Status.PENDING_APPROVAL){
            status = Status.ACTIVE;
        }

    }

    @PostUpdate
    private void postUpdate() {
        if(approvalNote == null && approvedAt !=null && approvedBy != null && status == Status.ACTIVE){
            approvedBy = new AuthenticationFacade().getAuthentication().getName();
            approvedAt = LocalDateTime.now();
            if(approvalNote == null){
                approvalNote = "Customer approved by " + approvedBy + " at " + approvedAt;
            }
        }
    }
}
