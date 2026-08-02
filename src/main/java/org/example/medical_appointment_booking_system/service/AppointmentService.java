package org.example.medical_appointment_booking_system.service;


import org.apache.coyote.BadRequestException;
import org.example.medical_appointment_booking_system.dto.AppointmentCompletionDTO;
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

import java.time.LocalDate;
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

       int currentBooking = appointmentRepo.countByDoctorIdAndAppointmentDateAndStatusNot(
               appointmentRequestDTO.getDoctorId(),
               appointmentRequestDTO.getAppointmentDate(),
               "CANCELED"
       );

       if (currentBooking>5){
           throw new RuntimeException("Doctor has reached maximum appointment limit (5) for this date!");
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

    public String appointmentReschedule(Integer appointId,LocalDate newAppointmentDate){
        Appointment appointment = appointmentRepo.findById(appointId)
                .orElseThrow(()->new NotFoundException("Appointment not found"));

        if(appointment.getStatus().equals("CANCELLED")||appointment.getStatus().equals("COMPLETED")){
            throw new RuntimeException("Cannot reschedule a CANCELLED or COMPLETED appointment!");
        }
        if(newAppointmentDate==null){
            throw new RuntimeException("Appointment date cannot be null");
        }
        if(newAppointmentDate.isBefore(appointment.getAppointmentDate())){
            throw new RuntimeException("Appointment date cannot be before appointment date!");
        }
        if(newAppointmentDate.equals(appointment.getAppointmentDate())){
            throw new RuntimeException("Appointment date already set to that date!");

        }
        boolean isDoctorBooked = appointmentRepo.existsByDoctorIdAndAppointmentDate(appointment.getDoctor().getId(), newAppointmentDate);
        if(isDoctorBooked){
            throw new RuntimeException("Doctor already booked in this date!");

        }
        appointment.setAppointmentDate(newAppointmentDate);
        appointment.setStatus("RESCHEDULED");
        appointmentRepo.save(appointment);
        return "APPOINTMENT SUCCESSFULLY RESCHEDULED";

    }

    public AppointmentCompletionDTO completeAppointment(AppointmentCompletionDTO appointmentCompletionDTO){
        Appointment appointment = appointmentRepo.findById(appointmentCompletionDTO.getAppointmentId())
                .orElseThrow(()->new NotFoundException("Appointment not found"));

        if("CANCELLED".equals(appointment.getStatus())){
            throw new RuntimeException("Cannot Complete a CANCELLED appointment!");
        }

        appointment.setNotes(appointmentCompletionDTO.getNotes());
        appointment.setStatus("COMPLETED");
         Appointment newAppointment = appointmentRepo.save(appointment);
         return modelMapper.map(newAppointment,AppointmentCompletionDTO.class);


    }

}
