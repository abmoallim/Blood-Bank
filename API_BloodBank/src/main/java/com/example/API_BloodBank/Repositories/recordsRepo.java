package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface recordsRepo extends JpaRepository<records,Long> {
    @Query(value = "SELECT SUM(r.quantity) as total_blood FROM records r WHERE 1",nativeQuery = true)
    int getTotalRecord();
}
