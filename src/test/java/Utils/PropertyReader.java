package Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyReader {
    private static PropertyReader propertyReader;
    private static final Logger logger = Logger.getLogger("Utils.PropertyReader");
    private PropertyReader(){}
    public static PropertyReader getInstance(){
        if(propertyReader==null){
            propertyReader = new PropertyReader();
        }
        return propertyReader;
    }

    public String getProperty(String key){
        try{
            Properties prop = new Properties();
            FileInputStream inputStream = new FileInputStream("src/test/resources/Dev.properties");
            prop.load(inputStream);
            return prop.getProperty(key);
        } catch (Exception e) {
            logger.log(Level.SEVERE,e.getMessage());
            return "Error occured in reading properties file";
        }
    }
}
