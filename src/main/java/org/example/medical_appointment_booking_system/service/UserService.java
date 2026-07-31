package org.example.medical_appointment_booking_system.service;

import org.example.medical_appointment_booking_system.dto.UserDTO;
import org.example.medical_appointment_booking_system.entity.User;
import org.example.medical_appointment_booking_system.exception.NotFoundException;
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
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;
    @Autowired
    private ModelMapper modelMapper;

    public UserDTO registerUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRole("Patient");
        newUser.setFullName(userDTO.getFullName());
        newUser.setEmail(userDTO.getEmail());
        newUser.setContactNumber(userDTO.getContactNumber());

        User savedUser = userRepo.save(newUser);
        return modelMapper.map(savedUser, UserDTO.class);

    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepo.findAll();
        return modelMapper.map(users,new TypeToken<List<UserDTO>>() {}.getType());

    }

    public UserDTO getUserById(Integer id) {
        User user = userRepo.findById(id)
                .orElseThrow(()->new NotFoundException("User not found"));
        return modelMapper.map(user,UserDTO.class);
    }
}
