package uct.so.bloodbank_API.Controllers;

import DTO.DoonerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.donors;
import uct.so.bloodbank_API.Services.donorService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/donors")
public class donorsController {
    @Autowired
    donorService _DonorSer;

    @GetMapping("/")
    public List<donors> getAllDonors(){
        return _DonorSer.getList();
    }
    @GetMapping("/{id}")
    public donors getByID(@PathVariable Long id){
        return  _DonorSer.getById(id);
    }
    @PostMapping("/")
    public donors AddNew(@RequestBody donors donor){
        return _DonorSer.addNew(donor);
    }

    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _DonorSer.deleteBy(id);
    }

}
