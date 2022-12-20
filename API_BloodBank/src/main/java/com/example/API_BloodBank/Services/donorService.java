package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.donor;
import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Repositories.donorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class donorService {
    @Autowired
    donorRepo _repo;

    public List<donor> getAll(){
        return _repo.findAll();
    }

    public donor getByID(Long id){
        return _repo.findById(id).get();
    }

    public donor addNew(donor donors){
        return _repo.save(donors);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }

    public Long numOfDonors(){
        Long num = _repo.findAll().stream().count();

        return  num;
    }
}
