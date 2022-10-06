package com.mycompany.propertymanagement.converter;

import java.util.Formatter;

public class HexEncoder {

    public static String toHex(byte[] data) {
        StringBuffer sb = new StringBuffer(data.length*2);
        try(Formatter formatter = new Formatter(sb)) {
            for(byte b: data) {
                formatter.format("%02x", b);
            }
        } catch(Exception e) {
            e.getMessage();
        }

        return sb.toString();
    }

}


