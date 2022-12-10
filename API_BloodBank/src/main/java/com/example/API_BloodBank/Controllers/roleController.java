package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.hospital;
import com.example.API_BloodBank.Models.role;
import com.example.API_BloodBank.Services.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class roleController {
    @Autowired
    roleService _service;

    @GetMapping("/")
    public List<role> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public role GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public role save(@RequestBody role Role){
        return _service.addNew(Role);
    }

    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }
}
