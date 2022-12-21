package com.example.API_BloodBank.Services;

import com.example.API_BloodBank.DTO.MobileDTO;
import com.example.API_BloodBank.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class mobileService {
    @Autowired
    recipientRepo _reci;

    @Autowired
    donorRepo _donor;

    @Autowired
    hospitalRepo _hosp;

    @Autowired
    donationRepo bloodIn;
    @Autowired
    recordsRepo bloodOut;

    public List<MobileDTO> getMobileInfo(){

        MobileDTO[] _mobile = new  MobileDTO[4];

        String[] backgrounds = {"circlebacgroundpurple","circlebackgroundgreen","circlebackgroundyellow","circlebackgroundred"};
        String[] icons = {"ic_baseline_local_hospital_24","ic_baseline_people_24","ic_baseline_people_24","ic_baseline_bloodtype_24"};
        String[] names = {"Hospitals","Donors","Recipients","Total Blood"};

        Long hosp = _hosp.findAll().stream().count();
        Long donor = _donor.findAll().stream().count();
        Long reci = _reci.findAll().stream().count();

        Integer b = ( bloodIn.getTotalDonations()- bloodOut.getTotalRecord());


        Long blood = b.longValue();

        Long[] totals = {hosp,donor,reci,blood};


        for (int i = 0; i < _mobile.length; i++) {
            MobileDTO dto = new MobileDTO();
            dto.setBackground(backgrounds[i]);
            dto.setName(names[i]);
            dto.setIcon(icons[i]);
            dto.setTotal(totals[i]);

            _mobile[i] = dto;

        }
        return Arrays.stream(_mobile).toList();
    }

}
