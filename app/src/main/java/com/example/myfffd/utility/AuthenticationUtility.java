package com.example.myfffd.utility;

import android.util.Patterns;

import java.util.HashMap;
import java.util.Map;

public final class AuthenticationUtility {

    public static Map<String, String> articleMapOne;

    static {
        articleMapOne = new HashMap<>();
        articleMapOne.put("ar01", "Intro to Map");
        articleMapOne.put("ar02", "Some article");
    }

    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.isEmpty();
    }

    public static boolean isPasswordValid(String password) {
        return (password != null) && (password.trim().length() >= 6);

    }


}
