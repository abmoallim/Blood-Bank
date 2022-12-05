package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.bloodType;
import uct.so.bloodbank_API.Services.bloodTypeService;

import java.awt.color.ICC_ProfileRGB;
import java.util.List;

@RestController
@RequestMapping("/api/blood")
public class bloodTypeController {
    @Autowired
    bloodTypeService _bloodServ;

    @GetMapping("/")
    public List<bloodType> getAll(){
        return _bloodServ.getList();
    }
    @GetMapping("/{id}")
    public bloodType getByID(@PathVariable Long id){
        return  _bloodServ.getById(id);
    }
    @PostMapping("/")
    public bloodType AddNew(@RequestBody bloodType blood){
        return _bloodServ.addNew(blood);
    }

    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _bloodServ.deleteBy(id);
    }
}
