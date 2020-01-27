package sk.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentConfig {
    Properties properties;

    public EnvironmentConfig(String filePath) {
        properties = new Properties();
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            InputStream input = classloader.getResourceAsStream(String.format("%s.properties", filePath));
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Read value using the logical name as Key and return String
    public String getStringProperty(String key){
        return properties.getProperty(key);
    }

    // Read value using the logical name as Key and return Long
    public Long getLongProperty(String key){
        return Long.valueOf(properties.getProperty(key));
    }
}
