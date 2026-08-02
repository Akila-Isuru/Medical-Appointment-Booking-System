package org.example.medical_appointment_booking_system.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentRequestDTO {

    @NotBlank(message = "Patient ID is required")
    private Integer patientId;
    @NotBlank(message = "Doctor ID is required")
    private Integer doctorId;
    @NotBlank(message = "Appointment ID is required")
    private LocalDate appointmentDate;


}
