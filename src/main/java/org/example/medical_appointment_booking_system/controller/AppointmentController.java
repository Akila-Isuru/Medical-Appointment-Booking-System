package org.example.medical_appointment_booking_system.controller;

import jakarta.validation.Valid;
import org.example.medical_appointment_booking_system.dto.AppointmentRequestDTO;
import org.example.medical_appointment_booking_system.entity.Appointment;
import org.example.medical_appointment_booking_system.service.AppointmentService;
import org.example.medical_appointment_booking_system.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<StandardResponse> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentDTO){
        AppointmentRequestDTO newAppointment = appointmentService.createAppointment(appointmentDTO);
        return new ResponseEntity<>(
                new StandardResponse(201,"Appointment Placed Successfully !",newAppointment), HttpStatus.CREATED

        );

    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<StandardResponse> updateApppointment(@PathVariable Integer appointmentId){
        String updateAppointment = appointmentService.cancelAppointment(appointmentId);
        return new ResponseEntity<>(
                new StandardResponse(200,"Appointment Updated Successfully !",updateAppointment), HttpStatus.OK
        );
    }

    @GetMapping("/getByPatientId/{patientId}")
    public ResponseEntity<StandardResponse> getByPatientId(@PathVariable Integer patientId){
        List<AppointmentRequestDTO> appointmentRequestDTOS = appointmentService.getAppointmentByPatientId(patientId);
        return new ResponseEntity<>(
                new StandardResponse(200,"Appointment Requested Successfully !",appointmentRequestDTOS), HttpStatus.OK
        );
    }

    @GetMapping("/getByDoctorId/{doctorId}")
    public ResponseEntity<StandardResponse> getByDoctorId(@PathVariable Integer doctorId){
        List<AppointmentRequestDTO> appointmentRequestDTOS = appointmentService.getAppointmentByDoctorId(doctorId);
        return new ResponseEntity<>(
                new StandardResponse(200,"Appointment Requested Successfully !",appointmentRequestDTOS), HttpStatus.OK
        );
    }



}
