package uct.so.bloodbank_API.Services;

import DTO.DoonerDto;
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
    public DoonerDto covertyEntityDto(donors doner) {

        DoonerDto doonerDto = new DoonerDto();
        doonerDto.setId(doner.getId());
        doonerDto.setName(doner.getName());
        doonerDto.setPhone(doner.getPhone());
        doonerDto.setBrithDate(doner.getBrithDate());
        doonerDto.setAddress(doner.getAddress());
        doonerDto.setWeight(doner.getWeight());
        doonerDto.setBloodName(doner.getBloodType().getBloodName());
        doonerDto.setBlood_id(doner.getBloodType().getId());
        doonerDto.setState_id(doner.getState().getId());
        doonerDto.setStateName(doner.getState().getStateName());
        doonerDto.setUserName(doner.getUser().getUserName());
        doonerDto.setUser_id(doner.getUser().getId());

        return doonerDto;
    }}
