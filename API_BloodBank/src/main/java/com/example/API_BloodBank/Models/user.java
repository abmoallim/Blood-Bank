package com.example.API_BloodBank.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name="role_id", referencedColumnName = "id")
//    @JsonIgnore
    private role role;

}
