package lab3.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    protected static FileInputStream fileIS;
    protected static Properties properties;

    static {
        try {
            fileIS = new FileInputStream("./lab3/src/main/resources/lab3.properties");
            properties = new Properties();
            properties.load(fileIS);

        } catch (IOException exception){
            exception.printStackTrace();
        } finally {
            if (fileIS != null){
                try {
                    fileIS.close();
                } catch (IOException exception){
                    exception.printStackTrace();
                }
            }
        }
    }

    public static String getProp(String key){
        return properties.getProperty(key);
    }
}
