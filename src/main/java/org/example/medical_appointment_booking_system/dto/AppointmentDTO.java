package org.example.medical_appointment_booking_system.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medical_appointment_booking_system.entity.Doctor;
import org.example.medical_appointment_booking_system.entity.User;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentDTO {
    private Integer id;
    private LocalDate appointmentDate;
    private String status; //(PENDING, CONFIRMED, CANCELLED, COMPLETED)
    private Doctor doctor;
    private User patient;
}
