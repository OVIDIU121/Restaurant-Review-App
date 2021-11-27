package com.example.myfffd.utility;

import com.example.myfffd.models.User;

public class Session {

    public static class ActiveSession
    {
        public static User user;
        public static String cookie;
        public static String option;
        public static void logout()
        {
            option = null;
            user = null; // reset session class
        }

        public static void clearOption(){
            option = null;
            cookie = null;
        }

        public static boolean checkLogin ()
        {
            if (user.getAuth_id().isEmpty()){
                return false;
            }
            else {
                return true;
            }
        }

    }

}