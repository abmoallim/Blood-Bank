package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.bloodType;
import uct.so.bloodbank_API.Modals.states;
import uct.so.bloodbank_API.Repositories.statesRepo;
import uct.so.bloodbank_API.Services.bloodTypeService;
import uct.so.bloodbank_API.Services.stateService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/states")
public class stateController {

    @Autowired
    stateService _repo;

    @GetMapping("/")
    public List<states> getAllStates(){
        return _repo.getList();
    }

    @GetMapping("/{id}")
    public states getByID(@PathVariable Long id){
        return _repo.getById(id);
    }
    @PostMapping("/")
    public states AddNew(@RequestBody states state){
        return _repo.addNew(state);
    }

    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _repo.deleteBy(id);
    }

}
