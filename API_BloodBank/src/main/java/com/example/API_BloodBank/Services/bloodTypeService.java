package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.bloodType;
import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Repositories.bloodTypeRepo;
import com.example.API_BloodBank.Repositories.testResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bloodTypeService {
    @Autowired
    bloodTypeRepo _repo;

    public List<bloodType> getAll(){
        return _repo.findAll();
    }

    public bloodType getByID(Long id){
        return _repo.findById(id).get();
    }

    public bloodType addNew(bloodType blood){
        return _repo.save(blood);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }
}
