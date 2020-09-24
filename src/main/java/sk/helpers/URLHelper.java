package sk.helpers;

public class URLHelper {
    public static String addUserPassToURL(String user, String password, String predefinedURL) {
        String appUPL = predefinedURL.length() > 0 ? predefinedURL : "www";
        return appUPL.replace("://", "://" + user + ":" + password + "@");
    }
}
