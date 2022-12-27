package com.example.API_BloodBank.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userDTO {
    private Long id;
    private String username;

    private String status;
    private String rol;
    private int response_code;
}
