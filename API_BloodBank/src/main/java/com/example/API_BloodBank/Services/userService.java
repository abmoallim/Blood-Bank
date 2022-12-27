package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.DTO.userDTO;
import com.example.API_BloodBank.Models.user;
import com.example.API_BloodBank.Repositories.userRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class userService {
    @Autowired
    userRepo _repo;

    public List<user> getAll(){
        return _repo.findAll();
    }

    public user getByID(Long id){
        return _repo.findById(id).get();
    }

    public user addNew(user users){
        return _repo.save(users);
    }
    public void deleteBy(Long id){
        _repo.deleteById(id);
    }


    public List<userDTO> getUserByUsername(String name,String password){

        user user = _repo.getByUser(name, password);

        userDTO[] dtos = new userDTO[1];

        for (int i = 0; i < dtos.length; i++) {
            if(user !=null) {
                userDTO dtoArray = new userDTO();
                dtoArray.setId(user.getId());
                dtoArray.setUsername(user.getUsername());
                dtoArray.setStatus(user.getStatus());
                dtoArray.setRol(user.getRole().getRole_name());
                dtoArray.setResponse_code(200);

                dtos[i] = dtoArray;
            }
        }

        return Arrays.stream(dtos).toList();


    }


}
