package org.example.medical_appointment_booking_system.repo;

import jakarta.validation.constraints.NotBlank;
import org.example.medical_appointment_booking_system.entity.Appointment;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
    boolean existsByDoctorIdAndAppointmentDate(Integer doctorId, LocalDate appointmentDate);

    int countByDoctorIdAndAppointmentDateAndStatusNot(Integer doctorId, LocalDate appointmentDate, String status);
    List<Appointment> getAppointmentsByPatientId(Integer patientId);
    List<Appointment> getAppointmentsByDoctorId(Integer doctorId);

}
