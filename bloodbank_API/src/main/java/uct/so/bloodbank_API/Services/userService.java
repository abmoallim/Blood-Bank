package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.users;
import uct.so.bloodbank_API.Repositories.usersRepo;


import java.util.List;

@Service
public class userService {
    @Autowired
    usersRepo _userRepo;

    public List<users> getAllUsers(){
        return _userRepo.findAll();
    }
    public users getUserById(Long id){
        return _userRepo.findById(id).get();
    }
    public users addNew(users user){
        return _userRepo.save(user);
    }
    public void deleteRole(Long id){
        _userRepo.deleteById(id);
    }

}
