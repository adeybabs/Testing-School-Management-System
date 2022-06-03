package com.example.appraisalProject.service;

import com.example.appraisalProject.entity.Staff;
import com.example.appraisalProject.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff>getAllStaffs(){
        return staffRepository.findAll();
    }

    public void addNewStaff(Staff staff) {
        Optional<Staff> staffOptional = staffRepository.findStaffByEmail(staff.getEmail());
        if(staffOptional.isPresent()){
            throw
                    new IllegalStateException("email taken");

        }
        staffRepository.save(staff);
    }


    public void deleteStaff(Integer staffId) {
        staffRepository.existsById(staffId);
        if(!staffRepository.existsById(staffId)){
            throw new IllegalStateException("id does not exist");
        }
        staffRepository.deleteById(staffId);
    }

    @Transactional
    public void updateStaff(Integer staffId, String firstName, String lastName, String email) {

        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new IllegalStateException(
                "staff with id" + staffId + "does not exist"));

        if (firstName != null && firstName.length() > 0 && !Objects.equals(
                staff.getFirstName(), firstName
        )) {
            staff.setFirstName(firstName);
        }

        if (lastName != null && lastName.length() > 0 && !Objects.equals(
                    staff.getLastName(), lastName
        )) {
            staff.setLastName(lastName);
        }

        if (email != null && email.length() > 0 && !Objects.equals(staff.getEmail(), email)){
            Optional<Staff> staffOptional = staffRepository.findStaffByEmail(email);
            if (staffOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            staff.setEmail(email);

        }

//            staff.setFirstName(firstName);
//            staff.setLastName(lastName);
            }

        }




