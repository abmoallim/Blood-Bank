package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.records;
import uct.so.bloodbank_API.Services.recordService;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/donations")
public class recordsController {
    @Autowired
    recordService _Ser;

    @GetMapping("/")
    public List<records> getAll(){
        return _Ser.getList();
    }
    @GetMapping("/{id}")
    public records getByID(@PathVariable Long id){
        return  _Ser.getById(id);
    }
    @PostMapping("/")
    public records AddNew(@RequestBody records record){
        return _Ser.addNew(record);
    }

    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _Ser.deleteBy(id);
    }
}
