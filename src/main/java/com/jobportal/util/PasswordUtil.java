package com.jobportal.util;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordUtil {
    private static final int ITERATIONS = 120_000;
    private static final int KEY_LENGTH = 256;
    private static final SecureRandom RANDOM = new SecureRandom();

    private PasswordUtil() {}

    public static String hash(String password) {
        try {
            byte[] salt = new byte[16];
            RANDOM.nextBytes(salt);
            byte[] hash = derive(password.toCharArray(), salt);
            return ITERATIONS + ":" + Base64.getEncoder().encodeToString(salt) + ":"
                    + Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new IllegalStateException("Could not hash password", e);
        }
    }

    public static boolean verify(String password, String stored) {
        try {
            String[] parts = stored.split(":");
            int iterations = Integer.parseInt(parts[0]);
            byte[] salt = Base64.getDecoder().decode(parts[1]);
            byte[] expected = Base64.getDecoder().decode(parts[2]);
            byte[] actual = derive(password.toCharArray(), salt, iterations);
            if (actual.length != expected.length) return false;
            int diff = 0;
            for (int i = 0; i < actual.length; i++) diff |= actual[i] ^ expected[i];
            return diff == 0;
        } catch (Exception e) {
            return false;
        }
    }

    private static byte[] derive(char[] password, byte[] salt) throws Exception {
        return derive(password, salt, ITERATIONS);
    }

    private static byte[] derive(char[] password, byte[] salt, int iterations) throws Exception {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, KEY_LENGTH);
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
    }
}
