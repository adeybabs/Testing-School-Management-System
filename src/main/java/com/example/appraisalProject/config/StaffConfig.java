package com.example.appraisalProject.config;

import com.example.appraisalProject.entity.Staff;
import com.example.appraisalProject.repository.StaffRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class StaffConfig {

    @Bean
    CommandLineRunner commandLineRunner(StaffRepository staffRepository){
        return args -> {
            Staff austin = new Staff(
                    1, "Austin", "James", "austin@mail.com", "Sales"
            );
            Staff faith = new Staff(
                    2, "faith", "Don", "faith@mail.com", "HR"
            );
            Staff laura = new Staff(
                    3, "laura", "Anderson", "laura@mail.com", "Product"
            );
            Staff richard = new Staff(
                    4, "Richard", "Williams", "richard@mail.com", "Tech"
            );
            staffRepository.saveAll(List.of(austin,faith,laura,richard));

        };
    }
}
