package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.roles;
import uct.so.bloodbank_API.Repositories.roleRepo;

import java.util.List;

@Service
public class roleService {
    @Autowired
    roleRepo _Repo;

    public List<roles> getRolesList(){
        return _Repo.findAll();
    }
    public roles getRoleById(Long id){
        return _Repo.findById(id).get();
    }
    public roles addNewRole(roles role){
        return _Repo.save(role);
    }
    public void deleteRole(Long id){
        _Repo.deleteById(id);
    }


}
