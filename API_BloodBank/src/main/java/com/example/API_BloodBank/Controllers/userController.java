package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.user;
import com.example.API_BloodBank.Services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/user")
public class userController {

    @Autowired
    userService _service;
    @GetMapping("/")
    public List<user> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public user GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public user save(@RequestBody user User){
        return _service.addNew(User);
    }



    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
         _service.deleteBy(id);
    }



}
