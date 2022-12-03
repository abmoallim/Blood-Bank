package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uct.so.bloodbank_API.Modals.donors;
import uct.so.bloodbank_API.Repositories.donorsRepo;
import uct.so.bloodbank_API.Services.donorService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/donors")
public class donorsController {
    @Autowired
    donorsRepo _Repo;

    @GetMapping("/")
    public List<donors> getAllDonors(){
        return _Repo.findAll();
    }

}
