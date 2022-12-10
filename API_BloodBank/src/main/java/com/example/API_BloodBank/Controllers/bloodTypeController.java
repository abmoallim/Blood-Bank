package com.example.API_BloodBank.Controllers;


import com.example.API_BloodBank.Models.bloodType;
import com.example.API_BloodBank.Models.user;
import com.example.API_BloodBank.Services.bloodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/blood")
public class bloodTypeController {

    @Autowired
    bloodTypeService _service;
    @GetMapping("/")
    public List<bloodType> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public bloodType GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public bloodType save(@RequestBody bloodType bloodTypes){
        return _service.addNew(bloodTypes);
    }



    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }
}
