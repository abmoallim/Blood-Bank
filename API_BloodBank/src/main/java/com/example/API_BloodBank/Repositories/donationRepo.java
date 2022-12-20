package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface donationRepo extends JpaRepository<donation,Long> {
    @Query(value = "SELECT SUM(d.quantity) as total_blood FROM donation d WHERE 1",nativeQuery = true)
    int getTotalDonations();
}
