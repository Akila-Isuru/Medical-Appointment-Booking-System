package org.example.medical_appointment_booking_system;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MedicalAppointmentBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalAppointmentBookingSystemApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
