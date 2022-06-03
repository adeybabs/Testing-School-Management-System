package com.example.appraisalProject.repository;

import com.example.appraisalProject.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//responsible for data access
@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

    //query to select s from staff s where s.email = ?!
    Optional<Staff> findStaffByEmail(String email);

}
