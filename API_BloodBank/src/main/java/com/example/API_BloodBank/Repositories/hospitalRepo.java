package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface hospitalRepo extends JpaRepository<hospital,Long> {
}
