package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.donation;
import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Repositories.donationRepo;
import com.example.API_BloodBank.Repositories.testResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class donationService {
    @Autowired
    donationRepo _repo;

    public List<donation> getAll(){
        return _repo.findAll();
    }

    public donation getByID(Long id){
        return _repo.findById(id).get();
    }

    public donation addNew(donation donations){
        return _repo.save(donations);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }
}
