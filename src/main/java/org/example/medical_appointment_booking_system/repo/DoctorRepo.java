package org.example.medical_appointment_booking_system.repo;

import org.example.medical_appointment_booking_system.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepo extends JpaRepository<Doctor,Integer> {

    List<Doctor> findBySpecialization(String specialization);

}
