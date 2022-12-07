package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.donations;
import uct.so.bloodbank_API.Services.donationService;


import java.util.List;

@RestController
public class donationsController {

    @Autowired
    donationService _Ser;

    @GetMapping("/")
    public List<donations> getAll(){
        return _Ser.getList();
    }
    @GetMapping("/{id}")
    public donations getByID(@PathVariable Long id){
        return  _Ser.getById(id);
    }
    @PostMapping("/")
    public donations AddNew(@RequestBody donations donation){
        return _Ser.addNew(donation);
    }

    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _Ser.deleteBy(id);
    }
}
