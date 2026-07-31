package org.example.medical_appointment_booking_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentRequestDTO {

    private Integer patientId;
    private Integer doctorId;
    private LocalDate appointmentDate;


}
