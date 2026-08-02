package org.example.medical_appointment_booking_system.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;
    @NotBlank(message = "Role cannot be blank")
    private String role; //(ADMIN, DOCTOR, PATIENT)
    @NotBlank(message = "Name cannot be blank")
    private String fullName;
    @Email(message = "Invalid email")
    private String email;
    @NotBlank(message = "Contact cannot be Blank")
    private String contactNumber;
    
}
