package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.bloodType;
import uct.so.bloodbank_API.Repositories.bloodTypeRepo;


import java.util.List;

@Service
public class bloodTypeService {
    @Autowired
    bloodTypeRepo _Repo;

    public List<bloodType> getList(){
        return _Repo.findAll();
    }
    public bloodType getById(Long id){
        return _Repo.findById(id).get();
    }
    public bloodType addNew(bloodType bloodTypes){
        return _Repo.save(bloodTypes);
    }
    public void deleteRole(Long id){
        _Repo.deleteById(id);
    }
}
