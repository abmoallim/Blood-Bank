package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.hospital;
import com.example.API_BloodBank.Services.hospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/hospital")
public class hospitalController {
    @Autowired
    hospitalService _service;

    @GetMapping("/")
    public List<hospital> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public hospital GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public hospital save(@RequestBody hospital Hospital){
        return _service.addNew(Hospital);
    }

    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }

    @GetMapping("/hospitals/")
    public Long GetNumberOfHospitals(){

        return  _service.numOfHospitals();

    }
}
