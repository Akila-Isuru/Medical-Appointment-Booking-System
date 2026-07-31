package org.example.medical_appointment_booking_system.service;


import org.example.medical_appointment_booking_system.dto.DoctorDTO;
import org.example.medical_appointment_booking_system.entity.Doctor;
import org.example.medical_appointment_booking_system.entity.User;
import org.example.medical_appointment_booking_system.exception.NotFoundException;
import org.example.medical_appointment_booking_system.repo.DoctorRepo;
import org.example.medical_appointment_booking_system.repo.UserRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.internal.bytebuddy.description.method.MethodDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DoctorService {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;

    public DoctorDTO registerDoctor(DoctorDTO doctorDTO) {

        User user = new User();
        user.setUsername(doctorDTO.getUsername());
        user.setPassword(doctorDTO.getPassword());
        user.setRole("DOCTOR");
        user.setFullName(doctorDTO.getFullName());
        user.setEmail(doctorDTO.getEmail());
        user.setContactNumber(doctorDTO.getContactNumber());


        User savedUser = userRepo.save(user);

        Doctor doctor = new Doctor();
        doctor.setSpecialization( doctorDTO.getSpecialization());
        doctor.setConsultationFee(doctorDTO.getConsultationFee());
        doctor.setUser(savedUser);

        Doctor savedDoctor = doctorRepo.save(doctor);

        return modelMapper.map(savedDoctor,DoctorDTO.class);
    }

    public List<DoctorDTO> getAllDoctors(){
        List<Doctor> doctors = doctorRepo.findAll();

        return modelMapper.map(doctors,new TypeToken<List<DoctorDTO>>(){}.getType());

    }

    public List<DoctorDTO> getDoctorBySpecialization(String specialization){
        List<Doctor> doctors = doctorRepo.findBySpecialization(specialization);
        return modelMapper.map(doctors,new TypeToken<List<DoctorDTO>>(){}.getType());
    }

    public DoctorDTO updateDoctorSpecializationAndFee(DoctorDTO doctorDTO){
        Doctor doctor = doctorRepo.findById(doctorDTO.getId())
                .orElseThrow(()->new NotFoundException("Doctor not found"));

        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setConsultationFee(doctorDTO.getConsultationFee());
        Doctor updatedDoctor = doctorRepo.save(doctor);
        return  modelMapper.map(updatedDoctor,DoctorDTO.class);
    }
}
