package utilities;

//import static org.hamcrest.Matchers.*;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import pojoClasses.LoginPayload;

public class CreateRequest {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public static String createLoginPayload(String new_username, String new_password) throws IOException {
		
		// Load JSON request body from file
        File payloadFile = new File("src/test/resources/" + "payloads/login.json");
        LoginPayload loginPayload = objectMapper.readValue(payloadFile, LoginPayload.class);

        // Update username in the Java object
        loginPayload.setUsername(new_username);  // Replace with dynamic username value
        loginPayload.setPassword(new_password);
        
        // Convert Java object back to JSON
        String updatedPayload = objectMapper.writeValueAsString(loginPayload);
		
		return updatedPayload;
	}
	 
}
