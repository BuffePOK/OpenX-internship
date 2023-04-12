package settings;

import data.Cart;
import data.ConvertFromJSONException;
import data.Product;
import data.User;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The ControlData class is responsible for managing the data of the application.
 * It provides methods to get and set the data, and to read and write the data from
 * and to different sources.
 */
public class ControlData {
    private static Cart[] carts;
    private static Product[] products;
    private static User[] users;

     /**
      * Private constructor to prevent instantiation.
      */
    private ControlData() {}

    /**
     * Get the array of carts.
     * @return The array of carts.
     */
    public static Cart[] getCarts() {
        return carts;
    }

    /**
     * Get the array of products.
     * @return The array of products.
     */
    public static Product[] getProducts() {
        return products;
    }

    /**
     * Get the array of users.
     * @return The array of users.
     */
    public static User[] getUsers() {
        return users;
    }

    /**
     * Retrieve data from API endpoints and write to data arrays.
     * @throws MalformedURLException If the URL of an API endpoint is invalid.
     * @throws ConvertFromJSONException If there is an error converting JSON data to Java objects.
     */
    public static void getInfoFromAPI() throws MalformedURLException, ConvertFromJSONException {
        URL productsURL = new URL(ReadProperties.properties.getProperty("URL-Products-API"));
        URL cartsURL = new URL(ReadProperties.properties.getProperty("URL-Carts-API"));
        URL usersURL = new URL(ReadProperties.properties.getProperty("URL-Users-API"));

        JSONArray productsJSON = new JSONArray(readPage(productsURL));
        JSONArray cartsJSON = new JSONArray(readPage(cartsURL));
        JSONArray usersJSON = new JSONArray(readPage(usersURL));

        writeData(productsJSON, cartsJSON, usersJSON);
    }

    /**
     * Write data to data arrays.
     * @param productsJSON The JSON array containing products data.
     * @param cartsJSON The JSON array containing carts data.
     * @param usersJSON The JSON array containing users data.
     * @throws ConvertFromJSONException If there is an error converting JSON data to Java objects.
     */
    public static void writeData(JSONArray productsJSON, JSONArray cartsJSON, JSONArray usersJSON) throws ConvertFromJSONException {
        products = new Product[productsJSON.length()];
        carts = new Cart[cartsJSON.length()];
        users = new User[usersJSON.length()];

        for (int i = 0; i < productsJSON.length(); i++)
            products[i] = Product.fromJSON(productsJSON.getJSONObject(i));

        for (int i = 0; i < cartsJSON.length(); i++)
            carts[i] = Cart.fromJSON(cartsJSON.getJSONObject(i));

        for (int i = 0; i < usersJSON.length(); i++)
            users[i] = User.fromJSON(usersJSON.getJSONObject(i));
    }

    /**
     * Read a web page from a given URL.
     * @param url The URL of the web page to read.
     * @return The contents of the web page.
     * @throws RuntimeException If there is an error reading the web page.
     */
    private static String readPage(URL url) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()))) {
            StringBuilder answerBuilder = new StringBuilder();
            String inputLine;

            while ((inputLine = bufferedReader.readLine()) != null)
                answerBuilder.append(inputLine);

            return answerBuilder.toString();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
