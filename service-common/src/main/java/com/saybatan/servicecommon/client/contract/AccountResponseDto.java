package com.saybatan.servicecommon.client.contract;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountResponseDto {

    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Date birthDate;

    public String getFullName() {
        return this.name + " " + this.surname;
    }
}
