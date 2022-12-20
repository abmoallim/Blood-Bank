package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.recipient;
import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Repositories.recipientRepo;
import com.example.API_BloodBank.Repositories.testResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class recipientService {
    @Autowired
    recipientRepo _repo;

    public List<recipient> getAll(){
        return _repo.findAll();
    }

    public recipient getByID(Long id){
        return _repo.findById(id).get();
    }

    public recipient addNew(recipient recipients){
        return _repo.save(recipients);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }

    public Long numOfRecipients(){
        Long num = _repo.findAll().stream().count();

        return  num;
    }
}
