package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.users;
import uct.so.bloodbank_API.Repositories.usersRepo;


import java.util.List;

@Service
public class userService {
    @Autowired
    usersRepo _Repo;

    public List<users> getList(){
        return _Repo.findAll();
    }
    public users getById(Long id){
        return _Repo.findById(id).get();
    }
    public users addNew(users user){
        return _Repo.save(user);
    }
    public void deleteRole(Long id){
        _Repo.deleteById(id);
    }

}
