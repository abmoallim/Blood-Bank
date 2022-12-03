package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.users;
import uct.so.bloodbank_API.Repositories.usersRepo;

import java.util.List;
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class usersController {
    @Autowired
    usersRepo _userRe;
    @GetMapping("/")
    public List<users> getAllUsers(){
        return _userRe.findAll();
    }

}
