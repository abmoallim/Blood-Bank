package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.records;
import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Repositories.recordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class recordsService {

    @Autowired
    recordsRepo _repo;

    public List<records> getAll(){
        return _repo.findAll();
    }

    public records getByID(Long id){
        return _repo.findById(id).get();
    }

    public records addNew(records record){
        return _repo.save(record);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }

    
}
