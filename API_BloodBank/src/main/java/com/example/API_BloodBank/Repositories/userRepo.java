package com.example.API_BloodBank.Repositories;

import com.example.API_BloodBank.Models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userRepo extends JpaRepository<user,Long> {


    @Query(value = "SELECT * FROM user u WHERE u.username = :username AND u.password = :password", nativeQuery = true)
     user getByUser(String username, String password);
}
