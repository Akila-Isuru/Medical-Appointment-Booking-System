package org.example.medical_appointment_booking_system.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String role; //(ADMIN, DOCTOR, PATIENT)
    private String fullName;
    private String email;
    private String contactNumber;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    private List<Appointment> appointments;

}
