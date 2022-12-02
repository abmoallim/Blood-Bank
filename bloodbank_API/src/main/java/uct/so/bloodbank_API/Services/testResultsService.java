package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uct.so.bloodbank_API.Modals.testResults;

import uct.so.bloodbank_API.Repositories.testResultsRepo;

import java.util.List;

@Service
public class testResultsService {

    @Autowired
    testResultsRepo _Repo;

    public List<testResults> getList(){
        return _Repo.findAll();
    }
    public testResults getById(Long id){
        return _Repo.findById(id).get();
    }
    public testResults addNew(testResults testResult){
        return _Repo.save(testResult);
    }
    public void deleteRole(Long id){
        _Repo.deleteById(id);
    }

}
