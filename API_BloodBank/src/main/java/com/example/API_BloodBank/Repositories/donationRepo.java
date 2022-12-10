package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface donationRepo extends JpaRepository<donation,Long> {
}
