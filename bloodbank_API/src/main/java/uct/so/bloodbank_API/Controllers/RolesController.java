package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uct.so.bloodbank_API.Modals.roles;
import uct.so.bloodbank_API.Repositories.roleRepo;
import uct.so.bloodbank_API.Services.roleService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/role")
public class RolesController {
    @Autowired
    roleRepo _rolesSer;

    @GetMapping("/")
    public List<roles> getAll(){
       return  _rolesSer.findAll();
    }
}
