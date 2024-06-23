package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class MyConfig {

    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    public static String getProperty(String key) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            prop.load(input);
            return prop.getProperty(key);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void setProperty(String key, String value) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            prop.load(input);
            try (OutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
                prop.setProperty(key, value);
                prop.store(output, null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
