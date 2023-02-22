package sk.fmfi.listng.domain.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Util class used for encryption of sensitive data and verifying them
 */
public class Encryption {

    public static String encrypt(String input) throws Exception {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA3-256");
            final byte[] hashbytes = digest.digest(
                    input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashbytes);
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("{} not recognized as a encryption algorithm.");
        }
    }

    public static boolean validate(String raw, String encrypted) throws Exception {
        return encrypted.equals(encrypt(raw));
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

}
