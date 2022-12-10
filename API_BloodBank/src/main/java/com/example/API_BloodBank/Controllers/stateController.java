package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.hospital;
import com.example.API_BloodBank.Models.state;
import com.example.API_BloodBank.Services.stateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/state")
public class stateController {
    @Autowired
    stateService _service;

    @GetMapping("/")
    public List<state> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public state GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public state save(@RequestBody state State){
        return _service.addNew(State);
    }

    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }
}
