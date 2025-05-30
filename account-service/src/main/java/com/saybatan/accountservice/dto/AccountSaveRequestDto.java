package com.saybatan.accountservice.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AccountSaveRequestDto {

    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Date birthDate;
}
