package com.example.API_BloodBank.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private Date brithDate;
    private String phone;
    private String Weight;

    @ManyToOne(optional = false)
    @JoinColumn(name="blood_id", referencedColumnName = "id")
    private bloodType bloodType;
    @ManyToOne(optional = false)
    @JoinColumn(name="state_id", referencedColumnName = "id")
    private state state;

    private String username;
    private String password;
    private String Status;

    @OneToMany(mappedBy = "donor",fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JsonIgnore
    private List<testResult> testResults;



}
