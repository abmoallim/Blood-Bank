package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.donations;
import uct.so.bloodbank_API.Repositories.donationsRepo;


import java.util.List;

@Service
public class donationService {
    @Autowired
    donationsRepo _Repo;

    public List<donations> getList(){
        return _Repo.findAll();
    }
    public donations getById(Long id){
        return _Repo.findById(id).get();
    }
    public donations addNew(donations donation){
        return _Repo.save(donation);
    }
    public void deleteRole(Long id){
        _Repo.deleteById(id);
    }
}
