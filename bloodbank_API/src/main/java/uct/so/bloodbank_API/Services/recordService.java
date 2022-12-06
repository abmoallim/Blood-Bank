package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.records;
import uct.so.bloodbank_API.Repositories.recordsRepo;


import java.util.List;

@Service
public class recordService {
    @Autowired
    recordsRepo _Repo;

    public List<records> getList(){
        return _Repo.findAll();
    }
    public records getById(Long id){
        return _Repo.findById(id).get();
    }
    public records addNew(records record){
        return _Repo.save(record);
    }
    public void deleteBy(Long id){
        _Repo.deleteById(id);
    }
}
