package org.example.medical_appointment_booking_system.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_appointment_booking_system.entity.Appointment;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    private Integer id;
    private String username;
    private String password;
    private String role; //(ADMIN, DOCTOR, PATIENT)
    private String fullName;
    private String email;
    private String contactNumber;
    
}
