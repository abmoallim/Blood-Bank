package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.donation;
import com.example.API_BloodBank.Models.donor;
import com.example.API_BloodBank.Services.donationService;
import com.example.API_BloodBank.Services.donorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api/donor")
public class donorController {

    @Autowired
    donorService _service;
    @GetMapping("/")
    public List<donor> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public donor GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public donor save(@RequestBody donor donors){
        return _service.addNew(donors);
    }


    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }

    @GetMapping("/donors/")
    public Long GetNumberOfDonors(){

        return  _service.numOfDonors();

    }
}
