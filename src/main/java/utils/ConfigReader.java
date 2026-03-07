package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    // 🔹 Load config.properties once
    public static void initConfig() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            prop.load(ip);
            System.out.println("✅ Configuration loaded successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load configuration file.");
        }
    }

    // 🔹 Read a value by key
    public static String getProperty(String key) {
        if (prop == null) {
            initConfig();  // lazy load if not already loaded
        }
        return prop.getProperty(key);
    }
}
