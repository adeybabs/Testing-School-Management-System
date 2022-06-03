package com.example.appraisalProject.service;

import com.example.appraisalProject.entity.Staff;
import com.example.appraisalProject.repository.StaffRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class StaffServiceTest {

// mock staff repository
    @Mock private StaffRepository staffRepository;
    private StaffService underTest;

    @BeforeEach
    void setUp(){
        underTest = new StaffService(staffRepository);
    }


    @Test
    void canGetAllStaffs() {
        //when
        underTest.getAllStaffs();

        //then
        verify(staffRepository).findAll();


    }

    @Test
    void addNewStaff() {

        //Given
        Staff staff = new Staff("Funsho",
                "Will",
                "will@mail.com",
                "Sales");

        //when
        underTest.addNewStaff(staff);

        //then
        ArgumentCaptor<Staff> staffArgumentCaptor =
                ArgumentCaptor.forClass(Staff.class);

        verify(staffRepository).save(staffArgumentCaptor.capture());

       Staff capturedStaff = staffArgumentCaptor.getValue();

       assertThat(capturedStaff).isEqualTo(staff);
    }

    @Test
    void willThrowErrorForEmailTaken() {

        //Given
        Staff staff = new Staff("Funsho",
                "Will",
                "will@mail.com",
                "Sales"
        );




        given(staffRepository.findStaffByEmail(staff.getEmail()))
               .willReturn(Optional.of(staff));

        //when
        //Then
        assertThatThrownBy(() ->underTest.addNewStaff(staff))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("email taken");

        verify(staffRepository, never()).save(any());


    }

    @Test
    void deleteStaff() {

        //Given
        Staff staff = new Staff(
                1,
                "Funsho",
                "Will",
                "will@mail.com",
                "Sales"
        );
        given(staffRepository.existsById(staff.getId()))
                .willReturn(true);

        //When
        underTest.deleteStaff(1);


        //Then
        verify(staffRepository).deleteById(any());

    }

    @Test
    void  willThrowDeleteErrorIdNotExist() {

        //Given
        Staff staff = new Staff(
                1,
                "Funsho",
                "Will",
                "will@mail.com",
                "Sales"
        );
        given(staffRepository.existsById(staff.getId()))
                .willReturn(false);

        //when
        //Then
        assertThatThrownBy(() ->underTest.deleteStaff(staff.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("id does not exist");

        verify(staffRepository, never()).delete(any());

    }


    @Test
    @Disabled
    void updateStaff() {
    }
}