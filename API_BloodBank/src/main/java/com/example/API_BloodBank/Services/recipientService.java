package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.DTO.MobileDTO;
import com.example.API_BloodBank.DTO.recipientDTO;
import com.example.API_BloodBank.Models.recipient;
import com.example.API_BloodBank.Models.testResult;
import com.example.API_BloodBank.Repositories.recipientRepo;
import com.example.API_BloodBank.Repositories.testResultRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class recipientService {
    @Autowired
    recipientRepo _repo;

    public List<recipient> getAll(){
        return _repo.findAll();
    }

    public recipient getByID(Long id){
        return _repo.findById(id).get();
    }

    public recipient addNew(recipient recipients){
        return _repo.save(recipients);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }

    public Long numOfRecipients(){
        Long num = _repo.findAll().stream().count();

        return  num;
    }


    public List<recipientDTO> getByState(Long id){
        List<recipientDTO> _recipient = _repo.getByState(id)

                .stream().map(recipients->{
                    recipientDTO dto = new recipientDTO();
                    dto.setId(recipients.getId());
                    dto.setName(recipients.getName());
                    dto.setPhone(recipients.getPhone());
                    dto.setBrithDate(recipients.getBrithDate());
                    dto.setStatus(recipients.getStatus());
                    dto.setStateName(recipients.getState().getStateName());
                    dto.setBloodName(recipients.getBloodType().getBloodName());

                    return dto;
        }).collect(Collectors.toList());

        return _recipient;
    }


    public List<MobileDTO> infoForApp(){





        List<MobileDTO> _info = _repo.findAll()
                .stream().map(finalInfo ->{
                    MobileDTO mobile = new MobileDTO();
                    mobile.setBackground("circlebackgroundyellow");
                    mobile.setIcon("ic_baseline_people_24");
                    mobile.setName("Recipients");
                    mobile.setTotal(_repo.findAll().stream().count());


                    return mobile;
                }).collect(Collectors.toList());
        return _info;
    }
}
