package sk.fmfi.listng.user.application.util;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class SecretUtil {


    private static final long ONE_DAY_MILLIS = 1000L * 60 * 60 * 24;

    /**
     * Create an encrypted string based for current date.
     * With every day we have a new secret that is generated.
     *
     * @return encrypted String for current day.
     */
    public static String generateSecret() {
        return generateSecretFromTime(new Date());
    }

    /**
     * Create an encrypted string based on on provided date.
     * With this we ensure that every 24 the secret value is brand new.
     *
     * @param date Date instance used to generate the secret of the given day.
     * @return encrypted String
     */
    private static String generateSecretFromTime(Date date) {
        // DD/MM/YYYY 01:12:35:723
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 23);
        calendar.set(Calendar.SECOND, 35);
        calendar.set(Calendar.MILLISECOND, 723);

        long millis = calendar.getTimeInMillis();

        StringBuilder sb = new StringBuilder();
        sb.append(millis * calendar.get(Calendar.WEEK_OF_YEAR) * calendar.get(Calendar.DAY_OF_WEEK));

        long seed = (long) calendar.get(Calendar.YEAR)
                * calendar.get(Calendar.MONTH)
                * calendar.get(Calendar.DAY_OF_YEAR)
                * calendar.get(Calendar.DAY_OF_MONTH)
                * calendar.get(Calendar.WEEK_OF_MONTH);

        Random random = new Random(seed);

        for (int i = 0; i < 32; i++) {
            int num = random.nextInt(35, 123);

            if (i % 5 > 0) {
                sb.append((char) num);
            } else {
                sb.append(num);
            }
        }

        return sb.toString();
    }

    public static boolean isValid(String test){
        if (isCurrent(test)) {
            return true;
        }
        
        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.MINUTE) < 30 && now.get(Calendar.HOUR_OF_DAY) == 0) {
            return isYesterdays(test);
        }
        
        return false;
    }
    
    private static boolean isCurrent(String test) {
        return test.equals(generateSecret());
    }

    private static boolean isYesterdays(String test) {
        Date yesterday = new Date(new Date().getTime() - ONE_DAY_MILLIS);
        return test.equals(generateSecretFromTime(yesterday));
    }
}
