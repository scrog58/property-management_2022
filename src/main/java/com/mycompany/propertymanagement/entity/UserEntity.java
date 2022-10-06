package com.mycompany.propertymanagement.entity;


import com.mycompany.propertymanagement.controller.HashManager;
import com.mycompany.propertymanagement.service.HashingUserPasswords;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="USER_TABLE")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator="native")
    private Long id;

    private String ownerName;

    @Column(name = "EMAIL", nullable = false)
    private String ownerEmail;
    private String phone;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
       return password;
    }

    public void setPassword(String password) {
       this.password = password;
    }
}
