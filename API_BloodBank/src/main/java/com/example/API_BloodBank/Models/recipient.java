package com.example.API_BloodBank.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class recipient  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String phone;
    private Date brithDate;


    @ManyToOne(optional = false)
    @JoinColumn(name="blood_id", referencedColumnName = "id")
    private bloodType bloodType;
    @ManyToOne(optional = false)
    @JoinColumn(name="state_id", referencedColumnName = "id")
    private state state;

    private String username;
    private String password;
    private String Status;



}
