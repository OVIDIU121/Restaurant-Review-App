package com.example.myfffd.utility;

import android.text.TextUtils;
import android.util.Patterns;

import java.util.HashMap;
import java.util.Map;

public final class AuthenticationUtility {

    public static boolean isEmailValid(String email) {
        if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.isEmpty())
        {
            return true;
        }
        else return false;
    }

    public static boolean isPasswordValid(String password) {
        if ((password != null) && (password.trim().length() >= 6))
        {
            return true;
        }
        return false;

    }
    public static Map<String, String> articleMapOne;
    static {
        articleMapOne = new HashMap<>();
        articleMapOne.put("ar01", "Intro to Map");
        articleMapOne.put("ar02", "Some article");
    }


}
