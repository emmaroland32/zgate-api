package com.eradiuxtech.customerservice.entity.shared;


import lombok.Data;

@Data
public class KeycloakUser {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String username;
    protected String password;
    protected String role;
    protected String realmName;
}
