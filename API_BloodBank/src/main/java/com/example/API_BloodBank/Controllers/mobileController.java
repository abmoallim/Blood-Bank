package com.example.API_BloodBank.Controllers;

import com.example.API_BloodBank.DTO.MobileDTO;
import com.example.API_BloodBank.Services.mobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/mobile")
public class mobileController {

    @Autowired
    mobileService _mobile;
@GetMapping("/")
    public List<MobileDTO> get_mobile() {
        return _mobile.getMobileInfo();
    }
}
