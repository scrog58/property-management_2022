package com.mycompany.propertymanagement.controller;


import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.service.HashingUserPasswords;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;



    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO) {
        userDTO = userService.register(userDTO);

        ResponseEntity<UserDTO> responseEntity = new ResponseEntity<>(userDTO, HttpStatus.CREATED);

        return responseEntity;
    }

    @PostMapping(path = "/login", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserDTO userDTO) {
        String password = userDTO.getPassword();

        try {
            password = HashManager.toHexHash(password, HashingUserPasswords.SHA256);

        } catch(Exception e) {
            e.getMessage();
        }


        userDTO = userService.login(userDTO.getOwnerEmail(), password);

        return new ResponseEntity<>(userDTO, HttpStatus.OK);

    }

}
