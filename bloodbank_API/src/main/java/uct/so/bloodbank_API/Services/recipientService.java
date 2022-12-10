package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.recipients;

import uct.so.bloodbank_API.Repositories.recipientsRepo;


import java.util.List;

@Service
public class recipientService {
    @Autowired
    recipientsRepo _Repo;

    public List<recipients> getList(){
        return _Repo.findAll();
    }
    public recipients getById(Long id){
        return _Repo.findById(id).get();
    }
    public recipients addNew(recipients recipient){
        return _Repo.save(recipient);
    }
    public void deleteBy(Long id){
        _Repo.deleteById(id);
    }

    public List<recipients> getByState(Long id){
        System.out.println(id);
        return _Repo.getByStateID(id);
    }
}
