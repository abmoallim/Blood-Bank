package uct.so.bloodbank_API.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uct.so.bloodbank_API.Modals.bloodType;
import uct.so.bloodbank_API.Services.bloodTypeService;

import java.util.List;

@RestController
@RequestMapping("/api/blood")
public class bloodTypeController {
    @Autowired
    bloodTypeService _bloodServ;

    @GetMapping("/")
    public List<bloodType> getAll(){
        return _bloodServ.getList();
    }
}
