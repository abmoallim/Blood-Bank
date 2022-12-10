package uct.so.bloodbank_API.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.donors;

import uct.so.bloodbank_API.Repositories.donorsRepo;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class donorService {
    @Autowired
    donorsRepo _Repo;

    public donors getById(Long id){
        return _Repo.findById(id).get();
    }
    public donors addNew(donors donor){
        return _Repo.save(donor);
    }
    public void deleteBy(Long id){
        _Repo.deleteById(id);
    }

    public List<donors> getList() {
        return _Repo.findAll();
    }

    public void UpdateUID(){
        System.out.println("called");
        _Repo.UpdateUID();
    }
   }
