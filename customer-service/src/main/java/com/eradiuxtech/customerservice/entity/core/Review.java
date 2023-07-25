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
public class Review extends Approve {

    @Column(name = "reviewed_by")
    protected String reviewedBy;

    @Column(name = "reviewed_at")
    protected LocalDateTime reviewedAt;

    @Column(name = "review_note")
    protected String reviewNote;

    @Column(name = "is_reviewed", columnDefinition = "boolean default false", nullable = false)
    protected Boolean isReviewed = false;

    @Column(name = "for_review", columnDefinition = "boolean default false", nullable = false)
    protected Boolean forReview = false;

    private void pushForReview() {
        if(!forReview){
            forReview = true;
        }
    }

    @PreUpdate
    private void PrePersist() {
        if(reviewNote == null && reviewedBy != null && reviewedAt != null && forReview){
            reviewNote = "Customer reviewed by " + reviewedBy + " at " + reviewedAt;
        }
        if(reviewedBy != null && reviewedAt != null && forReview){
            isReviewed = true;
            forApproval = true;
        }
    }


}
