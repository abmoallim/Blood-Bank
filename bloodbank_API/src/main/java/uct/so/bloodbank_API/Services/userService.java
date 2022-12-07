package uct.so.bloodbank_API.Services;

import DTO.DoonerDto;
import DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.Modals.donors;
import uct.so.bloodbank_API.Modals.users;
import uct.so.bloodbank_API.Repositories.usersRepo;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class userService {
    @Autowired
    usersRepo _userRepo;

    public users getUserById(Long id){
        return _userRepo.findById(id).get();
    }
    public users addNew(users user){
        return _userRepo.save(user);
    }
    public void deleteBy(Long id){
        _userRepo.deleteById(id);
    }

    public List<UserDTO> getAllUserDTO() {
        return _userRepo.findAll().stream().map(this::covertyEntityDto).collect(Collectors.toList());
    }
    public UserDTO covertyEntityDto(users users) {

        UserDTO userDTO= new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setEmail(users.getEmail());
        userDTO.setUserName(users.getUserName());
        userDTO.setPassword(users.getPassword());
        userDTO.setStatus(users.getStatus());
        userDTO.setRole_id(users.getId());


        return userDTO;
    }}



