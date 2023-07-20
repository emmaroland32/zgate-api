package com.eradiuxtech.zgate.controlservice.entity;


import com.eradiuxtech.zgate.controlservice.entity.core.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuditLog extends CoreEntity implements Serializable {

    String name;
}
