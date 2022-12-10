package uct.so.bloodbank_API.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uct.so.bloodbank_API.DTO.userDTO;
import uct.so.bloodbank_API.Modals.donors;
import uct.so.bloodbank_API.Modals.users;
import uct.so.bloodbank_API.Repositories.usersRepo;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class userService {
    @Autowired
    usersRepo _userRepo;

    public users getUserById(Long id) {
        return _userRepo.findById(id).get();
    }

    public users addNew(users user) {
        return _userRepo.save(user);
    }

    public void deleteBy(Long id) {
        _userRepo.deleteById(id);
    }

    public List<users> getAllUser() {
        return _userRepo.findAll();
    }

<<<<<<< HEAD
        UserDTO userDTO= new UserDTO();
        userDTO.setId(users.getId());
        userDTO.setEmail(users.getEmail());
        userDTO.setUserName(users.getUserName());
        userDTO.setPassword(users.getPassword());
        userDTO.setStatus(users.getStatus());
        userDTO.setRole_id(users.getId());
        userDTO.setRole(users.getRole().getRole_name());
=======
    public List<users> GetDonorUsers(Long id) {
        System.out.println(_userRepo.GetDonorUsers(id));
        return _userRepo.GetDonorUsers(id);
    }
>>>>>>> a64ce9daacbc9346bb22c4af77fbd3f846f103d0

    public List<userDTO> getDonorUsersDTO(Long id){
        List<userDTO> _users = _userRepo.GetDonorUsers(id)
                .stream()
                .map(user -> {
                    userDTO dto = new userDTO();
                    dto.setId(user.getId());
                    dto.setUserName(user.getUserName());
                    return dto;
                }).collect(Collectors.toList());

        return _users;
    }

}



