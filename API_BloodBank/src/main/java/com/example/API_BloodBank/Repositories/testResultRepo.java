package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.testResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface testResultRepo extends JpaRepository<testResult,Long> {
}
