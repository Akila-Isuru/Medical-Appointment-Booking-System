package org.example.medical_appointment_booking_system.controller;

import jakarta.validation.Valid;
import org.example.medical_appointment_booking_system.dto.DoctorDTO;
import org.example.medical_appointment_booking_system.entity.User;
import org.example.medical_appointment_booking_system.repo.DoctorRepo;
import org.example.medical_appointment_booking_system.service.DoctorService;
import org.example.medical_appointment_booking_system.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<StandardResponse> registerDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
        DoctorDTO newDoctor = doctorService.registerDoctor(doctorDTO);
        return new ResponseEntity<>(
                new StandardResponse(201,"Doctor Registerd Succesfully!",newDoctor), HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllDoctors(){
        List<DoctorDTO> doctorsList = doctorService.getAllDoctors();
        return new ResponseEntity<>(
                new StandardResponse(200,"Doctor List",doctorsList),HttpStatus.OK
        );
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<StandardResponse> getDoctorBySpecialization(@PathVariable String specialization){
        List<DoctorDTO> doctorList = doctorService.getDoctorBySpecialization(specialization);
        return new ResponseEntity<>(
                new StandardResponse(200,"Doctor List",doctorList),HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<StandardResponse> updateDoctor(@RequestBody DoctorDTO doctorDTO){
        DoctorDTO updatedDocter = doctorService.updateDoctorSpecializationAndFee(doctorDTO);
        return new ResponseEntity<>(
                new StandardResponse(200,"Doctor Updated Successfully!",updatedDocter),HttpStatus.OK
        );
    }




}
