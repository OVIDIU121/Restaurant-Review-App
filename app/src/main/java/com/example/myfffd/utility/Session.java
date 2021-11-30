package com.example.myfffd.utility;

import com.example.myfffd.models.User;

public class Session {

    public static class ActiveSession {
        public static User user;
        public static String cookie;
        public static String option;
        public static String forum_choice;

        public static void logout() {
            option = null;
            user = null; // reset session class
            forum_choice = null;
        }

        public static void clearOption() {
            option = null;
            cookie = null;
            forum_choice = null;
        }

        public static boolean checkLogin() {
            return !user.getAuth_id().isEmpty();
        }

    }

}