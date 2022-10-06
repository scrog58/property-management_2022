package com.mycompany.propertymanagement.service;

public enum HashingUserPasswords {

    SHA256("SHA-256");

    private String Value = "";

    HashingUserPasswords(String Value) {
        this.Value = Value;
    }

    @Override
    public String toString() {
        return Value;
    }
}
