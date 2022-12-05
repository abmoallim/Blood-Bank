package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.states;

import uct.so.bloodbank_API.Repositories.statesRepo;


import java.util.List;

@Service
public class stateService {
    @Autowired
    statesRepo _Repo;

    public List<states> getList(){
        return _Repo.findAll();
    }
    public states getById(Long id){
        return _Repo.findById(id).get();
    }
    public states addNew(states state){
        return _Repo.save(state);
    }
    public void deleteBy(Long id){
        _Repo.deleteById(id);
    }
}
