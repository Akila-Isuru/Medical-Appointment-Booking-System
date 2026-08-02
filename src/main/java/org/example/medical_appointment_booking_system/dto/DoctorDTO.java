package org.example.medical_appointment_booking_system.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_appointment_booking_system.entity.Appointment;
import org.example.medical_appointment_booking_system.entity.User;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorDTO {
    private Integer id;

    @NotBlank(message = "Specialization cannot be blank")
    private String specialization;

    @NotNull(message = "Consultation feee is required")
    @Positive(message = "Consultation fee must be greater than zero")
    private Double consultationFee;

    private Integer userId;
    @NotBlank(message = "Full name cannot be blank")
    private String fullName;
    @NotBlank(message = "User name cannot be blank")
    private String username;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    @Email(message = "Invalid email ")
    private String email;
    @NotBlank(message = "Contact NUmber cannot be blank")
    private String contactNumber;
}
