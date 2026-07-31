package org.example.medical_appointment_booking_system.config;

import org.example.medical_appointment_booking_system.dto.DoctorDTO;
import org.example.medical_appointment_booking_system.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.typeMap(Doctor.class, DoctorDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getUser().getId(), DoctorDTO::setUserId);
            mapper.map(src -> src.getUser().getFullName(), DoctorDTO::setFullName);
            mapper.map(src -> src.getUser().getUsername(), DoctorDTO::setUsername);
            mapper.map(src -> src.getUser().getEmail(), DoctorDTO::setEmail);
            mapper.map(src -> src.getUser().getContactNumber(), DoctorDTO::setContactNumber);
        });

        return modelMapper;
    }
}