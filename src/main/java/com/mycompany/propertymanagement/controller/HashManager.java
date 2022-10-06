package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.converter.HexEncoder;
import com.mycompany.propertymanagement.service.HashingUserPasswords;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashManager {
    public static byte[] toRawHash(byte[] data, HashingUserPasswords hashingUserPasswords) throws Exception {
        byte[] buffer = data;
        MessageDigest messageDigest = MessageDigest.getInstance(hashingUserPasswords.toString());
        messageDigest.reset();
        messageDigest.update(buffer);

        return messageDigest.digest();
    }

    public static String toHexHash(byte[] data, HashingUserPasswords hashingUserPasswords) throws Exception {
        return HexEncoder.toHex(toRawHash(data, hashingUserPasswords));

    }

    public static String toHexHash(String data, HashingUserPasswords hashingUserPasswords) throws Exception {

        return toHexHash(data.getBytes(StandardCharsets.UTF_8), hashingUserPasswords);
    }


}
