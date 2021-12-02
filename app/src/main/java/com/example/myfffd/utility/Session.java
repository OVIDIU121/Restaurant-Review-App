package com.example.myfffd.utility;

import com.example.myfffd.models.User;

/**
 * The type Session.
 */
public class Session {

    /**
     * The type Active session.
     */
    public static class ActiveSession {
        /**
         * The constant user.
         */
        public static User user;
        /**
         * The constant cookie.
         */
        public static String cookie;
        /**
         * The constant option.
         */
        public static String option;
        /**
         * The constant forum_choice.
         */
        public static String forum_choice;

        /**
         * Logout.
         */
        public static void logout() {
            option = null;
            user = null; // reset session class
            forum_choice = null;
        }

        /**
         * Clear option.
         */
        public static void clearOption() {
            option = null;
            cookie = null;
            forum_choice = null;
        }

        /**
         * Check login boolean.
         *
         * @return the boolean
         */
        public static boolean checkLogin() {
            return !user.getAuth_id().isEmpty();
        }

    }

}