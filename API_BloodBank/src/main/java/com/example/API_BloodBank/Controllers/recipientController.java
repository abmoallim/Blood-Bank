package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.Models.hospital;
import com.example.API_BloodBank.Models.recipient;
import com.example.API_BloodBank.Services.recipientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/recipient")
public class recipientController {
    @Autowired
    recipientService _service;


    @GetMapping("/")
    public List<recipient> GetAll(){
        return _service.getAll();
    }

    @GetMapping("/{id}")
    public recipient GetAll(@PathVariable Long id){
        return _service.getByID(id);
    }

    @PostMapping("/")
    public recipient save(@RequestBody recipient Recipient){
        return _service.addNew(Recipient);
    }

    @DeleteMapping("/{id}")
    public void deleteThis(@PathVariable Long id){
        _service.deleteBy(id);
    }

    @GetMapping("/recipients/")
    public Long GetNumberOfRecipients(){

        return  _service.numOfRecipients();

    }

}
