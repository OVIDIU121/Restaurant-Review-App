package com.example.myfffd.utility;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.example.myfffd.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Authentication utility.
 */
public final class AuthenticationUtility {


    /**
     * Is email valid boolean.
     *
     * @param email the email
     * @return the boolean
     */
    public static boolean isEmailValid(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.isEmpty();
    }

    /**
     * Is password valid boolean.
     *
     * @param password the password
     * @return the boolean
     */
    public static boolean isPasswordValid(String password) {
        return (password != null) && (password.trim().length() >= 6);

    }

}



