package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.donors;

import uct.so.bloodbank_API.Repositories.donorsRepo;


import java.util.List;

@Service
public class donorService {
    @Autowired
    donorsRepo _Repo;

    public List<donors> getList(){
        return _Repo.findAll();
    }
    public donors getById(Long id){
        return _Repo.findById(id).get();
    }
    public donors addNew(donors donor){
        return _Repo.save(donor);
    }
    public void deleteBy(Long id){
        _Repo.deleteById(id);
    }
}
