package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.bloodType;
import com.example.API_BloodBank.Models.donation;
import com.example.API_BloodBank.Services.donationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/donation")
public class donationController {

    @Autowired
    donationService _service;
    @GetMapping("/")
    public List<donation> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public donation GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @GetMapping("/donations/")
    public Long GetNumberOfDonations(){

        return  _service.numOfDonations();

    }

    @PostMapping("/")
    public donation save(@RequestBody donation donations){
        return _service.addNew(donations);
    }



    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }

    @GetMapping("/total/")
    public int GetTotal(){

        return  _service.getTotal();

    }
}
