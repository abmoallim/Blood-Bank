package uct.so.bloodbank_API.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.recipients;
import uct.so.bloodbank_API.Services.recipientService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/recipient")
public class recipientController {
    @Autowired
    recipientService _Ser;

    @GetMapping("/")
    public List<recipients> getAll(){
        return _Ser.getList();
    }
    @GetMapping("/{id}")
    public recipients getByID(@PathVariable Long id){
        return  _Ser.getById(id);
    }
    @PostMapping("/")
    public recipients AddNew(@RequestBody recipients recipient){
        return _Ser.addNew(recipient);
    }

    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _Ser.deleteBy(id);
    }

//    @GetMapping("/state/{id}")
//    public List<recipients> getByState(@PathVariable Long id){
//        return _Ser.getByState(id);
//    }
}
