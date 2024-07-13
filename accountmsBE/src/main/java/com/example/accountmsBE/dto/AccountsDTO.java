package com.example.accountmsBE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountsDTO {
    private int accID;
    private String accName;
    private String accEmail;
    private String accAddress;
}
