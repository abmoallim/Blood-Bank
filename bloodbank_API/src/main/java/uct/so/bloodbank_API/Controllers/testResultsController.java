package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.testResults;
import uct.so.bloodbank_API.Services.testResultsService;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/results")
public class testResultsController {

    @Autowired
    testResultsService _Ser;

    @GetMapping("/")
    public List<testResults> getAll(){
        return _Ser.getList();
    }
    @GetMapping("/{id}")
    public testResults getByID(@PathVariable Long id){
        return  _Ser.getById(id);
    }
    @PostMapping("/")
    public testResults AddNew(@RequestBody testResults result){
        return _Ser.addNew(result);
    }

    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _Ser.deleteBy(id);
    }


}
