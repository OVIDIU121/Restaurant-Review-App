package com.example.myfffd.utility;

import android.util.Patterns;

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

}
