package helpers;
import java.security.SecureRandom;

public class RandomString {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String RANDOM_STRING_DATA = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    public static String getRandomString () {
        String result = "";
        for (int i = 0; i < 5; i++) {
            result = generateRandomString(5);
        }
        return result;
    }

    public static String generateRandomString(int length) {
        if (length < 1) throw new IllegalArgumentException();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(RANDOM_STRING_DATA.length());
            char rndChar = RANDOM_STRING_DATA.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }
}
