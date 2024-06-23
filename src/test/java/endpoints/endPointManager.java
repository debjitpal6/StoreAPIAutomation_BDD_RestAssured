package endpoints;



//Centralized class to manage API endpoints. It can load endpoints from a configuration file or define them directly as constants
public class endPointManager {

	public static final String BASE_URL = "https://fakestoreapi.com";
    public static final String USERS_ENDPOINT = "/users";
    public static final String LOGIN_ENDPOINT = "/auth/login";
    public static final String PRODUCT_ENDPOINT = "/products";
    
    // Other endpoints...

    // Methods to get full URLs
    public static String getUsersEndpoint() {
        return BASE_URL + USERS_ENDPOINT;
    }
    
    public static String productEndpoint() {
        return BASE_URL + PRODUCT_ENDPOINT;
    }
    
    public static String productWithIDEndpoint(int productID) {
        return BASE_URL + PRODUCT_ENDPOINT + "/" + productID;
    }
    
    public static String loginEndpoint() {
        return BASE_URL + LOGIN_ENDPOINT;
    }
   
	
}
