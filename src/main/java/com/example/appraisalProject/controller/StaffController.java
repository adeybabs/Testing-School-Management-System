package com.example.appraisalProject.controller;

import com.example.appraisalProject.entity.Staff;
import com.example.appraisalProject.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/v1")
public class StaffController {

    private final StaffService staffService;

    @Autowired
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping(path = "/staff")
    public List<Staff> getAllStaffS(){
        return staffService.getAllStaffs();
    }

    @PostMapping(path="/add/staff")
    public void addNewStaff(@RequestBody Staff staff){
        staffService.addNewStaff(staff);
    }

    @DeleteMapping(path="remove/staff/{staffId}")
    public String deleteStaff(@PathVariable("staffId")Integer staffId ){
        staffService.deleteStaff(staffId);
        return "deleted";
    }

    @PutMapping(path="update/staff/{staffId}")
    public void updateStaff(
            @PathVariable("staffId")Integer staffId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String email){

        staffService.updateStaff(staffId, firstName, lastName, email);

    }




    }

