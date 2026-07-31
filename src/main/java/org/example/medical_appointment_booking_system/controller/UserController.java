package org.example.medical_appointment_booking_system.controller;

import org.example.medical_appointment_booking_system.dto.UserDTO;
import org.example.medical_appointment_booking_system.service.UserService;
import org.example.medical_appointment_booking_system.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<StandardResponse> registerUser(@RequestBody UserDTO userDTO){
         UserDTO newUser = userService.registerUser(userDTO);
        return  new ResponseEntity<>(
                new StandardResponse(201,"User Successfully Registered !",newUser), HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllUsers(){
        List<UserDTO> userDTOList = userService.getAllUsers();
        return  new ResponseEntity<>(
                new StandardResponse(200,"Users Successfully Loaded !",userDTOList), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StandardResponse> getUserById(@PathVariable Integer id){
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(
                new StandardResponse(200,"User Successfully Loaded !",user), HttpStatus.OK
        );
    }



}
