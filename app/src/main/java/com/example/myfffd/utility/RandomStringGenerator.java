package com.example.myfffd.utility;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class RandomStringGenerator {
    public static String RandomString() {
        byte[] array = new byte[10]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        return generatedString;
    }

}
