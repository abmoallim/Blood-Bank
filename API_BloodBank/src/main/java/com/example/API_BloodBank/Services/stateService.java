package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.Models.state;
import com.example.API_BloodBank.Models.testResult;

import com.example.API_BloodBank.Repositories.stateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class stateService {
    @Autowired
    stateRepo _repo;

    public List<state> getAll(){
        return _repo.findAll();
    }

    public state getByID(Long id){
        return _repo.findById(id).get();
    }

    public state addNew(state state){
        return _repo.save(state);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }
}
