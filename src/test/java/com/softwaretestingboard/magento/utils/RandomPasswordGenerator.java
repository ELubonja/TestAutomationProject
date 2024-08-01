package com.softwaretestingboard.magento.utils;

import java.security.SecureRandom;

public class RandomPasswordGenerator {
    private static final int PASSWORD_LENGTH = 12;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static String password;

        public static String generateRandomPassword() {
            StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

            for (int i = 0; i < PASSWORD_LENGTH; i++) {
                int randomAscii = RANDOM.nextInt(94) + 33; // ASCII range 33-126 that includes most special characters
                password.append((char) randomAscii);
            }
            return password.toString();
        }

        public static String getGeneratedPassword() {
            if (password == null) {
                password = generateRandomPassword();
            }
            return password;
        }
}
