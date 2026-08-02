package org.example.medical_appointment_booking_system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppointmentCompletionDTO {
    @NotBlank(message = "Appointment_id cannot be blank")
    private Integer appointmentId;
    @NotBlank(message = "Notes cannot be blank")
    private String notes;
}
