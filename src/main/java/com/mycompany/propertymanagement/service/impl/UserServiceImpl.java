package com.mycompany.propertymanagement.service.impl;

import com.mycompany.propertymanagement.converter.UserConverter;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.exception.ErrorModel;
import com.mycompany.propertymanagement.repository.UserRepository;
import com.mycompany.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO register(UserDTO userDTO) {
       Optional<UserEntity> optionalUserEntityEmail = userRepository.findByOwnerEmail(userDTO.getOwnerEmail());
        Optional<UserEntity> optionalUserEntityPhone = userRepository.findByPhone(userDTO.getPhone());

       if(optionalUserEntityEmail.isPresent() || optionalUserEntityPhone.isPresent()) {
           List<ErrorModel> errorModelList = new ArrayList<>();
           ErrorModel errorModel = new ErrorModel();
           errorModel.setCode("EMAIL_OR_PHONE_NUMBER_ALREADY_EXIST");
           errorModel.setMessage(("Email or Phone Number already exists please create a new one."));
           errorModelList.add(errorModel);

           throw new BusinessException(errorModelList);
       }

        UserEntity userEntity = userConverter.convertDTOtoEntity(userDTO);
        userEntity = userRepository.save(userEntity);
        userDTO = userConverter.convertEntitytoDTO(userEntity);

        return userDTO;
    }

    @Override
    public UserDTO login(String email, String password) {
        UserDTO userDTO = null;
        Optional<UserEntity> optionalUserEntity = userRepository.findByOwnerEmailAndPassword(email, password);
        if(optionalUserEntity.isPresent()) {
            userDTO = userConverter.convertEntitytoDTO(optionalUserEntity.get());

        } else {
            List<ErrorModel> errorModelList = new ArrayList<>();
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("INVALID_LOGIN");
            errorModel.setMessage(("Incorrect Email or Password"));
            errorModelList.add(errorModel);

            throw new BusinessException(errorModelList);
        }
        return userDTO;
    }
}
