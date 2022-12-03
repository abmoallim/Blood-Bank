package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.states;
import uct.so.bloodbank_API.Repositories.statesRepo;
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

}
