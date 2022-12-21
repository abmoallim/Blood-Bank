package com.example.API_BloodBank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class recipientDTO {

    private Long id;
    private String name;
    private String phone;
    private Date brithDate;
    private String Status;
    private String stateName;
    private String bloodName;

}
