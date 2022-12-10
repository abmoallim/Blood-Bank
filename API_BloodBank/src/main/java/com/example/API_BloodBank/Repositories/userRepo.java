package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<user,Long> {
}
