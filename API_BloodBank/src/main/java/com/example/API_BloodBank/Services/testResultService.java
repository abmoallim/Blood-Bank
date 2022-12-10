package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Models.user;
import com.example.API_BloodBank.Repositories.testResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class testResultService {
    @Autowired
    testResultRepo _repo;

    public List<testResult> getAll(){
        return _repo.findAll();
    }

    public testResult getByID(Long id){
        return _repo.findById(id).get();
    }

    public testResult addNew(testResult result){
        return _repo.save(result);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }
}
