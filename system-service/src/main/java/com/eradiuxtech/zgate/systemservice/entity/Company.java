package com.eradiuxtech.zgate.systemservice.entity;

import com.eradiuxtech.zgate.systemservice.entity.core.CoreEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company extends CoreEntity implements Serializable {

    private String name;
    private String shortName;
    private String address;

    private String phone;

    private String email;

    private String website;

    private String logo;

    private String description;

    private String facebook;

    private String twitter;


    private String instagram;

    private String youtube;

    private String linkedin;

    private String googlePlus;

    private String pinterest;

    private String skype;

    private String viber;

    private String whatsapp;

    private String telegram;

    private String tiktok;

    private String snapchat;


}
