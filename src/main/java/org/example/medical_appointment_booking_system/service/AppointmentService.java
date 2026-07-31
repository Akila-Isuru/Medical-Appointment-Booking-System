package org.example.medical_appointment_booking_system.service;


import org.example.medical_appointment_booking_system.dto.AppointmentRequestDTO;
import org.example.medical_appointment_booking_system.entity.Appointment;
import org.example.medical_appointment_booking_system.entity.Doctor;
import org.example.medical_appointment_booking_system.entity.User;
import org.example.medical_appointment_booking_system.exception.NotFoundException;
import org.example.medical_appointment_booking_system.repo.AppointmentRepo;
import org.example.medical_appointment_booking_system.repo.DoctorRepo;
import org.example.medical_appointment_booking_system.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepo appointmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private ModelMapper modelMapper;


    public AppointmentRequestDTO createAppointment(AppointmentRequestDTO appointmentRequestDTO){

        User patient = userRepo.findById(appointmentRequestDTO.getPatientId())
                .orElseThrow(()->new NotFoundException("Patient not found"));

        Doctor doctor = doctorRepo.findById(appointmentRequestDTO.getDoctorId())
                .orElseThrow(()->new NotFoundException("Doctor not found"));

        boolean isBooked = appointmentRepo.existsByDoctorIdAndAppointmentDate(appointmentRequestDTO.getDoctorId(), appointmentRequestDTO.getAppointmentDate());

        if(isBooked){
            throw  new RuntimeException("Doctor already booked in this date!");
        }

        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentRequestDTO.getAppointmentDate());
        appointment.setStatus("PENDING");
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment newAppointment = appointmentRepo.save(appointment);
        return  modelMapper.map(newAppointment,AppointmentRequestDTO.class);

    }

    public String cancelAppointment(Integer appointmentId){

        Appointment appointment = appointmentRepo.findById(appointmentId).
                orElseThrow(()->new NotFoundException("Appointment not found"));
        if(appointment.getStatus().equals("CANCELLED")){
            throw  new RuntimeException("Already canceled in this date!");
        }

        appointment.setStatus("CANCELLED");
        appointmentRepo.save(appointment);
        return "APPOINTMENT CANCELLED";

    }

    public List<AppointmentRequestDTO> getAppointmentByPatientId(Integer patientId){
        List<Appointment> appointments = appointmentRepo.getAppointmentsByPatientId(patientId);
        return  modelMapper.map(appointments,new TypeToken<List<AppointmentRequestDTO>>(){}.getType());
    }

    public List<AppointmentRequestDTO> getAppointmentByDoctorId(Integer doctorId){
        List<Appointment> appointments = appointmentRepo.getAppointmentsByDoctorId(doctorId);
        return modelMapper.map(appointments,new TypeToken<List<AppointmentRequestDTO>>(){}.getType());
    }
}
