package com.softwaretestingboard.magento.utils;

import java.util.Random;

public class RandomEmailGenerator {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz123456789";
    private static final String DOMAIN = "@example.com";
    private static final int EMAIL_LENGTH = 10;
    private static String email;

    public static String generateRandomEmail() {
        StringBuilder email = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < EMAIL_LENGTH; i++) {
            email.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        email.append(DOMAIN);
        return email.toString();
    }

    public static String getRandomEmail() {
        if (email == null) {
            email = generateRandomEmail();
        }
        return email;
    }
}
