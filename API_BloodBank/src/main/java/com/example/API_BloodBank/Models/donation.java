package com.example.API_BloodBank.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "donation")
public class donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name="result_id", referencedColumnName = "id")
    private testResult results;


    @ManyToOne(optional = false)
    @JoinColumn(name="hospital_id", referencedColumnName = "id")
    private hospital hospital;

    private Integer quantity;
    private Date date;


}
