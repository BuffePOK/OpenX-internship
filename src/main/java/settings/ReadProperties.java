package settings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A utility class for reading and accessing application properties from a file called "app-settings.xml".
 */
public class ReadProperties {
    /**
    * A {@link Properties} object containing the application properties read from the "app-settings.xml" file.
    */
    public static final Properties properties = readProperties();

    /**
     * Reads the application properties from the "app-settings.xml" file.
     *
     * @return A {@link Properties} object containing the application properties.
     * @throws RuntimeException If an error occurs while reading the properties file.
     */
    private static Properties readProperties() {
        try (InputStream inputStream = ReadProperties.class.getClassLoader().getResourceAsStream("app-settings.xml")) {
            Properties properties = new Properties();
            properties.loadFromXML(inputStream);

            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
