package com.eradiuxtech.customerservice.entity.core;

import com.eradiuxtech.customerservice.exception.CustomNotFoundException;
import com.eradiuxtech.customerservice.security.AuthenticationFacade;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class Review extends Approval {

    @Column(name = "reviewed_by")
    private String reviewedBy;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @Column(name = "review_note")
    private String reviewNote;


    public void forReview() {
        if(status == Status.ACTIVE){
            throw new CustomNotFoundException("Entity already approved", this.getId());
        }
        if(status == Status.REVIEWED){
            throw new CustomNotFoundException("Entity already reviewed", this.getId());
        }
        if(status == Status.REJECTED){
            throw new CustomNotFoundException("Entity already rejected", this.getId());
        }
        if(status == Status.PENDING){
            status = Status.PENDING_REVIEW;
        }
    }


    public void review() {
        if(status == Status.ACTIVE){
            throw new CustomNotFoundException("Entity already approved", this.getId());
        }
        if(status == Status.REVIEWED){
            throw new CustomNotFoundException("Entity already reviewed", this.getId());
        }
        if(status == Status.REJECTED){
            throw new CustomNotFoundException("Entity already rejected", this.getId());
        }
        if(status == Status.PENDING_REVIEW){
            status = Status.REVIEWED;
        }
    }

    @PostUpdate
    private void PostUpdate() {
        if(reviewNote == null && reviewedBy != null && reviewedAt != null && status == Status.PENDING_REVIEW){
            reviewedAt = LocalDateTime.now();
            reviewedBy = new AuthenticationFacade().getAuthentication().getName();
            if(reviewNote == null){
                reviewNote = "Customer reviewed by " + reviewedBy + " at " + reviewedAt;
            }
        }
    }


}
