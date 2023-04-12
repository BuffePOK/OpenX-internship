import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import algorithmes.CartHighestValue;
import algorithmes.ProductCategories;
import algorithmes.UsersFurthestLiving;
import algorithmes.interfaces.FurthestLiving;
import algorithmes.interfaces.HighestValue;
import algorithmes.interfaces.TotalValueCategory;
import data.Cart;
import data.ConvertFromJSONException;
import data.Product;
import data.User;

import org.json.JSONArray;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import settings.ControlData;

import static org.junit.Assert.*;

/**
 * This class contains methods to perform unit tests for various functionalities of the application.
 */
public class UnitTestOwn {
    /**
     * Initializes the data required for the tests.
     * @throws ConvertFromJSONException when the JSON data cannot be read or converted to the required format
     */
    @BeforeTest
    public void init() throws ConvertFromJSONException {
        JSONArray productsJSON = readJSON("products.json");
        JSONArray cartsJSON = readJSON("carts.json");
        JSONArray usersJSON = readJSON("users.json");

        ControlData.writeData(productsJSON, cartsJSON, usersJSON);
    }

    /**
     * Reads JSON data from the specified path.
     * @param path the path to the JSON file to be read
     * @return the JSONArray object representing the JSON data read from the file
     */
    private static JSONArray readJSON(String path) {
        try (InputStream inputStream = UnitTestOwn.class.getClassLoader().getResourceAsStream(path)) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String ans;
            StringBuilder sb = new StringBuilder();
            while ((ans = bufferedReader.readLine()) != null)
                sb.append(ans);

            bufferedReader.close();
            return new JSONArray(sb.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Tests whether the data read from the JSON files matches the data in the application.
     */
    @Test(testName = "Test 1 - JSON Comparison")
    public void isGoodReadingTest() {
        JSONArray productsTest = readJSON("products.json");
        JSONArray cartsTest = readJSON("carts.json");
        JSONArray usersTest = readJSON("users.json");

        JSONArray productsData = new JSONArray();
        JSONArray cartsData = new JSONArray();
        JSONArray usersData = new JSONArray();

        for(Product product: ControlData.getProducts())
            productsData.put(product.toJSON());

        for(Cart cart: ControlData.getCarts())
            cartsData.put(cart.toJSON());

        for(User user: ControlData.getUsers())
            usersData.put(user.toJSON());

        assertEquals(productsData.toString(), productsTest.toString());
        assertEquals(cartsData.toString(), cartsTest.toString());
        assertEquals(usersData.toString(), usersTest.toString());
    }

    /**
     * Tests whether the cart with the highest value in the application matches the expected result.
     */
    @Test(testName = "Test 2 - Cart with the highest value")
    public void highestValueCartTest() {
        User userTest = null;
        double valueTest = 8865.81;
        for(User user: ControlData.getUsers()) {
            if (user.id() == 0) {
                userTest = user;
                break;
            }
        }
        HighestValue.HighestValueUser answerTest = new HighestValue.HighestValueUser(userTest, valueTest);

        HighestValue value = new CartHighestValue();
        HighestValue.HighestValueUser answerData = value.findUser(ControlData.getProducts(), ControlData.getCarts(), ControlData.getUsers());

        assertEquals(answerData, answerTest);
    }

    /**
     * Tests whether the summary of product categories and their total value in the application matches the expected result.
     */
    @Test(testName = "Test 3 - Product categories and total value")
    public void summaryCategoriesTest() {
        Map<String, Double> testData = new HashMap<>();
        testData.put("Computers, Tablets & Phones", 2689.97);
        testData.put("Heating, Cooling & Air Quality", 99.99);
        testData.put("Ice Makers", 165.91);
        testData.put("Massage Tools & Equipment", 75.59);

        TotalValueCategory productCategories = new ProductCategories();
        productCategories.addProduct(ControlData.getProducts());
        Map<String, Double> answerData = productCategories.getTotalValues();

        assertEquals(testData, answerData);
    }

    /**
     * Tests if the method to find the two furthest living users is working correctly.
     */
    @Test(testName = "Test 4 - Two furthest users")
    public void furthestUsersLivingTest() {
        User user1 = null;
        User user2 = null;
        double distance = 141.42;

        for(User user: ControlData.getUsers()) {
            if(user.id() == 4) {
                user1 = user;
            } else if (user.id() == 5) {
                user2 = user;
                break;
            }
        }
        FurthestLiving.FurthestUsers answerTest = new FurthestLiving.FurthestUsers(user1, user2, distance);


        FurthestLiving furthest = new UsersFurthestLiving();
        FurthestLiving.FurthestUsers answerData = furthest.findFurthestLivingUsers(ControlData.getUsers());

        assertEquals(answerData, answerTest);
    }
}
