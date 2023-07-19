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
@Table(name = "current_connections")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentConnection extends CoreEntity implements Serializable {

    String name;
}
