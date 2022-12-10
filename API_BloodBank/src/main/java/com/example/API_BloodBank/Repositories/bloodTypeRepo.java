package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.bloodType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface bloodTypeRepo extends JpaRepository<bloodType,Long> {
}
