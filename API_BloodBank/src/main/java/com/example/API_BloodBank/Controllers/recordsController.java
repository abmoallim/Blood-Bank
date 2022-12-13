package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.hospital;
import com.example.API_BloodBank.Models.records;
import com.example.API_BloodBank.Services.recordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/records")
public class recordsController {
    @Autowired
    recordsService _service;
    @GetMapping("/")
    public List<records> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public records GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public records save(@RequestBody records record){
        return _service.addNew(record);
    }

    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }

    @GetMapping("/records/")
    public Long GetNumberOfRecords(){

        return  _service.numOfRecords();

    }

}
