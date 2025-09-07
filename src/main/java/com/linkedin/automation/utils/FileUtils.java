package com.linkedin.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtils {
    
    public static Properties loadProperties(String filePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading properties file: " + e.getMessage());
        }
        return properties;
    }
    
    public static String getProperty(String filePath, String key) {
        Properties properties = loadProperties(filePath);
        return properties.getProperty(key);
    }
}
