package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.roles;

import uct.so.bloodbank_API.Services.roleService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RolesController {
    @Autowired
    roleService _rolesSer;

    @GetMapping("/")
    public List<roles> getAll(){
       return  _rolesSer.getRolesList();
    }

    @PostMapping("/")
    public roles saveNew(@RequestBody roles role){
        return _rolesSer.addNewRole(role);
    }


    @GetMapping("/{id}")
    public roles getByID(@PathVariable Long id){
        return  _rolesSer.getRoleById(id);
    }


    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _rolesSer.deleteRole(id);
    }
}
