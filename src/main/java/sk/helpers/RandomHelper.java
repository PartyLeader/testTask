package sk.helpers;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomHelper {
    private static final int USER_NAME_SIZE = 10;
    private static final int PASSWORD_SIZE = 12;

    public static String randomString() {
        return randomString(USER_NAME_SIZE);
    }

    public static String randomString(int length) {
        String generatedString = RandomStringUtils.randomAlphabetic(length);
        return generatedString;
    }

    public static String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(PASSWORD_SIZE);
    }

    public static String randomMail() {
        int length = 12;
        int companySize = 12;
        String user = RandomStringUtils.randomAlphanumeric(length);
        String box = RandomStringUtils.randomAlphanumeric(companySize);
        return String.format("%s@%s.com", user, box);
    }
}
