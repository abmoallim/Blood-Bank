package com.example.API_BloodBank.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MobileDTO {

    private String background;
    private String icon;
    private String name;
    private Long total;
}
