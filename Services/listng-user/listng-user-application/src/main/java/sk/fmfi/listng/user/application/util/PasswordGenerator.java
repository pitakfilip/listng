package sk.fmfi.listng.user.application.util;

import java.security.SecureRandom;

public class PasswordGenerator {

    private final static String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    public static String create(){
        return create(14);
    }
        
    public static String create(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++)
        {
            int randomIndex = random.nextInt(ALPHA_NUMERIC.length());
            sb.append(ALPHA_NUMERIC.charAt(randomIndex));
        }

        return sb.toString();
    }
}
