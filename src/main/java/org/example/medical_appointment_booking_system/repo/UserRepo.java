package org.example.medical_appointment_booking_system.repo;

import org.example.medical_appointment_booking_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {


}
