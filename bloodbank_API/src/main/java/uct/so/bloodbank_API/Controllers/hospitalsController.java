package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.hospitals;
import uct.so.bloodbank_API.Modals.recipients;
import uct.so.bloodbank_API.Services.hospitalService;
import uct.so.bloodbank_API.Services.recipientService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/hospital")
public class hospitalsController {

    @Autowired
    hospitalService _Ser;

    @GetMapping("/")
    public List<hospitals> getAll(){
        return _Ser.getList();
    }
    @GetMapping("/{id}")
    public hospitals getByID(@PathVariable Long id){
        return  _Ser.getById(id);
    }
    @PostMapping("/")
    public hospitals AddNew(@RequestBody hospitals hospital){
        return _Ser.addNew(hospital);
    }

    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _Ser.deleteBy(id);
    }
@GetMapping("/name/{name}")
    public List<hospitals> getByName(@PathVariable String name){
        return _Ser.getName(name);
    }
}
