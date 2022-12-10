package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface recipientRepo extends JpaRepository<recipient,Long> {
}
