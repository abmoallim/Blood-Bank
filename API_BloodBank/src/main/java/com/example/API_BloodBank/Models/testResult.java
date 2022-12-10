package com.example.API_BloodBank.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class testResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name="donor_id", referencedColumnName = "id")
    private donor donor;

    @ManyToOne(optional = false)
    @JoinColumn(name="hospital_id", referencedColumnName = "id")
    private hospital hospital;

    private Boolean isHealthy;
    private String description;
    private Date date;

    @OneToMany(mappedBy = "results",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<donation> donationsList;


}
