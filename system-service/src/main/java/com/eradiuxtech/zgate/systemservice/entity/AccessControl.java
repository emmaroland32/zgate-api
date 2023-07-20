package com.eradiuxtech.zgate.systemservice.entity;


import com.eradiuxtech.zgate.systemservice.entity.core.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "access_control")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccessControl extends CoreEntity implements Serializable {

   private int minUsernameLength = 5;

   private Boolean require2fa = false;

    private Boolean allowLogin = true;

    private int maxLoginAttempts = 5;

    private int loginAttemptInterval = 5;

    private int sessionTimeout = 30;

    private int loginLockoutTime = 5;

    private int maxPasswordAge = 60;

    private int daysBeforePasswordExpiryWarning = 7;

    private int minPasswordLength = 8;

    private int passwordHistorySize = 30;

    private Boolean allowPasswordReuse = true;

    private Boolean allowPasswordChange = true;

    private Boolean allowPasswordReset = true;

    @OneToOne(optional = false)
    private Company company;

}
