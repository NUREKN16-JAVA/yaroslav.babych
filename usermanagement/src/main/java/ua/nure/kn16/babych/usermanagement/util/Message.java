package ua.nure.kn16.babych.usermanagement.util;

import java.util.ResourceBundle;

public class Message {
    private static final String resourceName = "messages";
    private static final ResourceBundle bundle = ResourceBundle.getBundle(resourceName);

    public static String getString(String key) {
        return bundle.getString(key);
    }

}
