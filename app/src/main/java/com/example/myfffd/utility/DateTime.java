package com.example.myfffd.utility;

import java.nio.charset.Charset;
import java.util.Random;

public class DateTime {
    public static String RandomString()
    {
        byte[] array = new byte[10]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

}
