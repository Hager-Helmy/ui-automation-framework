package utils;
import io.restassured.path.json.JsonPath;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JSONFileManager {
private final String jsonFilePath;
    public JSONFileManager(String jsonFilePath){
        this.jsonFilePath = jsonFilePath;
    }

    private JsonPath readJsonFile(){
        try{
            // Load the file from the classpath
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(jsonFilePath);
                    if (inputStream == null){
                        throw new RuntimeException("JSON file not found on the classpath: " +jsonFilePath);
                    }
            // Parse the JSON file
            return JsonPath.from(new InputStreamReader(inputStream));
        }
        catch (Exception e){
            throw new RuntimeException("Failed to read json file: " +jsonFilePath, e);

        }
    }
    public String getDataAsString(String jsonPath){
            JsonPath jsonPathParser = readJsonFile();
            // Extract the value
            return jsonPathParser.getString(jsonPath);

    }
    public <T> List <T> getDataAsList(String jsonPath, Class <T> type){
        JsonPath jsonPathParser = readJsonFile();
        return jsonPathParser.getList(jsonPath, type);
    }
}
