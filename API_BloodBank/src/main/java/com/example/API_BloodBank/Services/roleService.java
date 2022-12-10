package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.role;
import com.example.API_BloodBank.Models.testResult;

import com.example.API_BloodBank.Repositories.roleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class roleService {
    @Autowired
    roleRepo _repo;

    public List<role> getAll(){
        return _repo.findAll();
    }

    public role getByID(Long id){
        return _repo.findById(id).get();
    }

    public role addNew(role role){
        return _repo.save(role);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }
}
