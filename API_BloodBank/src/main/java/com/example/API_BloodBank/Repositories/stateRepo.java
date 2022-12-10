package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.state;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface stateRepo extends JpaRepository<state,Long> {
}
