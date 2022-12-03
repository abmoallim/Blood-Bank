package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.roles;
import uct.so.bloodbank_API.Repositories.roleRepo;

import java.util.List;

@Service
public class roleService {
    @Autowired
    roleRepo _rolesRepo;

    public List<roles> getRolesList(){
        return _rolesRepo.findAll();
    }
    public roles getRoleById(Long id){
        return _rolesRepo.findById(id).get();
    }
    public roles addNewRole(roles role){
        return _rolesRepo.save(role);
    }
    public void deleteRole(Long id){
        _rolesRepo.deleteById(id);
    }


}
