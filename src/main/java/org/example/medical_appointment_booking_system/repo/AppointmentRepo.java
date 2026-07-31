package org.example.medical_appointment_booking_system.repo;

import org.example.medical_appointment_booking_system.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<Appointment,Integer> {
    boolean existsByDoctorIdAndAppointmentDate(Integer doctorId, LocalDate appointmentDate);
    List<Appointment> getAppointmentsByPatientId(Integer patientId);
    List<Appointment> getAppointmentsByDoctorId(Integer doctorId);
}
