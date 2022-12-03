package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.hospitals;

import uct.so.bloodbank_API.Repositories.hospitalsRepo;


import java.util.List;

@Service
public class hospitalService {
    @Autowired
    hospitalsRepo _Repo;

    public List<hospitals> getList(){
        return _Repo.findAll();
    }
    public hospitals getById(Long id){
        return _Repo.findById(id).get();
    }
    public hospitals addNew(hospitals hospital){
        return _Repo.save(hospital);
    }
    public void deleteRole(Long id){
        _Repo.deleteById(id);
    }
}