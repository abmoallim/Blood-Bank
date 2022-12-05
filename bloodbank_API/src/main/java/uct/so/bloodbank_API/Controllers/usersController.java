package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uct.so.bloodbank_API.Modals.users;
import uct.so.bloodbank_API.Repositories.usersRepo;
import uct.so.bloodbank_API.Services.userService;

import java.util.List;
@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class usersController {
    @Autowired
    userService _userRe;
    @GetMapping("/")
    public List<users> getAllUsers(){
        return _userRe.getAllUsers();
    }
    @PostMapping("/")
    public users save(@RequestBody users user){
        return _userRe.addNew(user);
    }

    @GetMapping("/{id}")
    public users getByID(@PathVariable Long id){
        return  _userRe.getUserById(id);
    }
    @DeleteMapping("/{id}")
    public void  deleteByID(@PathVariable Long id){
        _userRe.deleteBy(id);
    }

}
