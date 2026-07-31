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
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String specialization;
    private Double consultationFee;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany(mappedBy ="doctor",cascade = CascadeType.ALL)
    private List<Appointment> appointments;


}
