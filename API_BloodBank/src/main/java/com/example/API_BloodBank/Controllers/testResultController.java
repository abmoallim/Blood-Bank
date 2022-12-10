package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.hospital;
import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Services.testResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/result")
@CrossOrigin
public class testResultController {
    @Autowired
    testResultService _service;

    @GetMapping("/")
    public List<testResult> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public testResult GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public testResult save(@RequestBody testResult result){
        return _service.addNew(result);
    }

    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }


}
