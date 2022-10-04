package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long userId;
    private String ownerName;

    @NotNull(message = "Owner Email is mandatory.")
    @NotEmpty(message = "Onwer Email cannot be empty")
    @Size(min = 1, max = 50, message = "Owner Email should be between 1 to 50 characters in length.")
    private String ownerEmail;

    private String phone;

    @NotNull(message = "Password is mandatory.")
    @NotEmpty(message = "Password cannot be empty")
    private String password;




}
