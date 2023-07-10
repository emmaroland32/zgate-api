package com.eradiuxtech.customerservice.convertor;
import com.eradiuxtech.customerservice.dto.request.ChangeStatusRequest;
import com.eradiuxtech.customerservice.entity.Customer;
import com.eradiuxtech.customerservice.util.Status;
import jakarta.ws.rs.BadRequestException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class ApprovalMapper {

     public Customer customerWorkFlow(Status type, ChangeStatusRequest changeStatusRequest, Customer data){

          if(type == Status.REVIEW){
               if(!data.getForReview() && data.getStatus() != Status.FOR_REVIEW){
                    throw new BadRequestException("Not Available for review");
               }
               if(data.getIsReviewed() && data.getStatus()== Status.REVIEWED){
                    throw new BadRequestException("Already reviewed");
               }
               if(data.getIsApproved() && data.getStatus() == Status.APPROVED){
                    throw new BadRequestException(("Already approved"));
               }
//                        if(Objects.equals(data.getCreatedBy(), _user.getUsername())){
//                            throw new BadRequestException("Cannot review an entity created by you");
//                        }
               data.setReviewNote(changeStatusRequest.getNote());
               data.setReviewedAt(LocalDateTime.now());
               data.setStatus(Status.REVIEWED);
               data.setReviewedBy("siyanda");
          } else if (type == Status.APPROVE){
               if(!data.getForApproval() && data.getStatus()!= Status.FOR_APPROVAL){
                    throw new BadRequestException("Awaiting Review");
               }
               if(!data.getIsReviewed() && data.getStatus() != Status.REVIEWED){
                    throw new BadRequestException("Entity has not been reviewed");
               }
               if(data.getIsApproved() && data.getStatus() == Status.APPROVED){
                    throw new BadRequestException(("Already approved"));
               }
//                        if(Objects.equals(data.getCreatedBy(), _user.getUsername())
//                                || Objects.equals(data.getReviewedBy(), _user.getUsername())){
//                            throw new BadRequestException("Cannot approve an Entity created by you");
//                        }
               data.setApproveNote(changeStatusRequest.getNote());
               data.setApprovedAt(LocalDateTime.now());
               data.setStatus(Status.APPROVED);
               data.setApprovedBy("siyanda");
          }
          else if (type == Status.REJECT) {
               if(data.getStatus() != Status.FOR_REVIEW){
                    throw new BadRequestException("Unavailable for rejection");
               }

               //                        if(Objects.equals(data.getCreatedBy(), _user.getUsername())
//                                || Objects.equals(data.getReviewedBy(), _user.getUsername())){
//                            throw new BadRequestException("Cannot reject an Entity created by you");
//                        }
               data.setRejectionNote(changeStatusRequest.getNote());
               data.setRejectedAt(LocalDateTime.now());
               data.setStatus(Status.REJECTED);
               data.setRejectedBy("siyanda");
          }else if(type == Status.FOR_REVIEW){
               data.setForReview(true);
               data.setStatus(Status.FOR_REVIEW);
          }
          else if(type == Status.FOR_APPROVAL){
               data.setForApproval(true);
               data.setStatus(Status.FOR_APPROVAL);
          }
          else {
             return data;
          }

         return data;
     }
}
