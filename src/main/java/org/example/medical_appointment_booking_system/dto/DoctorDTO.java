package org.example.medical_appointment_booking_system.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    private String specialization;
    private Double consultationFee;

    private Integer userId;
    private String fullName;
    private String username;
    private String password;
    private String email;
    private String contactNumber;
}
