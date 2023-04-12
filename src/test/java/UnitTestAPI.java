import algorithmes.CartHighestValue;
import algorithmes.ProductCategories;
import algorithmes.UsersFurthestLiving;
import algorithmes.interfaces.FurthestLiving;
import algorithmes.interfaces.HighestValue;
import algorithmes.interfaces.TotalValueCategory;
import data.ConvertFromJSONException;
import data.User;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import settings.ControlData;

import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * This class contains methods to perform unit tests for various functionalities of the application.
 */
public class UnitTestAPI {

    /**
     * Initializes the data required for the tests.
     * @throws MalformedURLException when the URL of an API endpoint is invalid.
     * @throws ConvertFromJSONException when the JSON data cannot be read or converted to the required format
     */
    @BeforeTest
    public void unit() throws MalformedURLException, ConvertFromJSONException {
        ControlData.getInfoFromAPI();
    }

    /**
     * Tests whether the cart with the highest value in the application matches the expected result.
     */
    @Test(testName = "Test 1 - Cart with the highest value")
    public void highestValueCartTest() {
        User userTest = null;
        double valueTest = 2578.7;
        for(User user: ControlData.getUsers()) {
            if (user.id() == 1) {
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
    @Test(testName = "Test 2 - Product categories and total value")
    public void summaryCategoriesTest() {
        Map<String, Double> testData = new HashMap<>();
        testData.put("women's clothing", 157.72);
        testData.put("electronics", 1994.99);
        testData.put("jewelery", 883.98);
        testData.put("men's clothing", 204.23);

        TotalValueCategory productCategories = new ProductCategories();
        productCategories.addProduct(ControlData.getProducts());
        Map<String, Double> answerData = productCategories.getTotalValues();

        assertEquals(testData, answerData);
    }

    /**
     * Tests if the method to find the two furthest living users is working correctly.
     */
    @Test(testName = "Test 3 - Two furthest users")
    public void furthestUsersLivingTest() {
        User user1 = null;
        User user2 = null;
        double distance = 144.02;

        for(User user: ControlData.getUsers()) {
            if(user.id() == 1) {
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
