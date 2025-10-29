import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static{
        try(FileInputStream FIS = new FileInputStream("config.properties")){
            properties.load(FIS);
        } catch(IOException e){
            throw new RuntimeException("Could not load config.properties file", e);
        }
    }

    public static String getApiKey(){
        String apiKey = properties.getProperty("API_KEY");
        if(apiKey == null){
            throw new RuntimeException("API_KEY not found in config.properties");
        }
        return apiKey;
    }
}
