package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {
    private Credentials credentials;
    public void loadCredentials(){

        ObjectMapper mapper = new ObjectMapper();
        try {
            credentials = mapper.readValue(new File("src\\test\\java\\data\\credentials.json"), Credentials.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load credentials from JSON file.");
        }
    }

    public Credentials getCredentials() {
        return credentials;
    }
}
