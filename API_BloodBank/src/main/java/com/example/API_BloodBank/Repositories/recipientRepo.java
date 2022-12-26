package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface recipientRepo extends JpaRepository<recipient,Long> {

    @Query(value = "SELECT * FROM recipient r WHERE r.state_id = :sid",nativeQuery = true)
    List<recipient> getByState(Long sid);
}
