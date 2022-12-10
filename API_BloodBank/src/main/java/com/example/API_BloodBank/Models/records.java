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
public class records {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name="recipient_id", referencedColumnName = "id")
    private recipient recipient;


    @ManyToOne(optional = false)
    @JoinColumn(name="hospital_id", referencedColumnName = "id")
    private hospital hospital;

    private Integer quantity;
    private Date date;


}
