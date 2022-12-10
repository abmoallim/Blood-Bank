package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface roleRepo extends JpaRepository<role,Long> {
}
