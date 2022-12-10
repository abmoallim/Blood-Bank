package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.hospital;
import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Repositories.hospitalRepo;
import com.example.API_BloodBank.Repositories.testResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class hospitalService {
    @Autowired
    hospitalRepo _repo;

    public List<hospital> getAll(){
        return _repo.findAll();
    }

    public hospital getByID(Long id){
        return _repo.findById(id).get();
    }

    public hospital addNew(hospital hospitals){
        return _repo.save(hospitals);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }
}
