package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.donor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface donorRepo extends JpaRepository<donor,Long> {
}
