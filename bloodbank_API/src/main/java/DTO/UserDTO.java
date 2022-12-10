package DTO;

import lombok.Data;

@Data

public class UserDTO {
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String status;
    private long role_id;
    private String role;

}
