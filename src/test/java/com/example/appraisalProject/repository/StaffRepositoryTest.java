package com.example.appraisalProject.repository;

import com.example.appraisalProject.entity.Staff;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StaffRepositoryTest {

    @Autowired
    private StaffRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }



    @Test
    void itShouldCheckIfFindingStaffByEmailWorks() {

        //given
        Staff staff = new Staff("Adey",
                "Babs",
                "babs@mail.com",
                "tech");

        underTest.save(staff);

        String email = "babs@mail.com";

        //when
        Staff expected = underTest.findStaffByEmail(staff.getEmail()).get();

        //then
        assertThat(expected).isEqualTo(staff);
    }

    @Test
    void itShouldCheckIfStaffEmailDoesNotExist(){
        //given
        String email = "dan@gmail.com";

        //when
        Optional<Staff> expected = underTest.findStaffByEmail(email);

        //then
        assertThat(expected).isNotEqualTo(email);

    }
}