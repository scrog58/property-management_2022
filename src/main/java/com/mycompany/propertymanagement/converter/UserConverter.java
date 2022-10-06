package com.mycompany.propertymanagement.converter;

import com.mycompany.propertymanagement.controller.HashManager;
import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.entity.UserEntity;
import com.mycompany.propertymanagement.service.HashingUserPasswords;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity convertDTOtoEntity(UserDTO userDTO)  {

        String password = userDTO.getPassword();

        try {
            password = HashManager.toHexHash(password, HashingUserPasswords.SHA256);
            System.out.println(HashManager.toHexHash(userDTO.getPassword(), HashingUserPasswords.SHA256));

        } catch(Exception e) {
            e.getMessage();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setOwnerEmail(userDTO.getOwnerEmail());
        userEntity.setOwnerName(userDTO.getOwnerName());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setPassword(password);

        return userEntity;

    }

    public UserDTO convertEntitytoDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userEntity.getId());
        userDTO.setOwnerEmail(userEntity.getOwnerEmail());
        userDTO.setOwnerName(userEntity.getOwnerName());
        userDTO.setPhone(userEntity.getPhone());
      //  userDTO.setPassword(userEntity.getPassword());

        return userDTO;

    }
}
