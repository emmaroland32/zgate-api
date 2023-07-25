package com.programming.eradiuxtech.entity;


import com.programming.eradiuxtech.entity.core.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "emails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email extends CoreEntity implements Serializable {

    String name;

    String email;

    String subject;

    String message;

    String status;

    String type;

    String toEmail;

    String fromEmail;

    String ccEmail;

    String bccEmail;

    String replyToEmail;

    String attachment;

    String attachmentName;

    String attachmentType;

    String attachmentSize;

    String attachmentPath;

    String attachmentStatus;


}
