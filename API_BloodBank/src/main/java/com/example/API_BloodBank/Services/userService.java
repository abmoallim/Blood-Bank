package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.user;
import com.example.API_BloodBank.Repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userService {
    @Autowired
    userRepo _repo;

    public List<user> getAll(){
        return _repo.findAll();
    }

    public user getByID(Long id){
        return _repo.findById(id).get();
    }

    public user addNew(user users){
        return _repo.save(users);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }


}
