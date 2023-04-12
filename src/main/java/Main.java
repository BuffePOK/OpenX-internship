import algorithmes.CartHighestValue;
import algorithmes.ProductCategories;
import algorithmes.UsersFurthestLiving;
import algorithmes.interfaces.FurthestLiving;
import algorithmes.interfaces.HighestValue;
import algorithmes.interfaces.TotalValueCategory;
import data.Cart;
import data.Product;
import data.User;
import settings.ControlData;

/**
 * This is the main class of the program, which initializes the data from API and applies the implemented algorithms.
 * It outputs the results on the console in a readable format.
 *
 * @author Dmytro Dychenko
 * @version 1.0.0
 * @since 12-apr-2023
 */
public class Main {
    /**
     * This is the main method of the program, which is responsible for initiating the data load from API and applying
     * implemented algorithms to get the desired output on the console.
     *
     * @param args command-line arguments passed to the program
     * @throws Exception in case of error during data loading or algorithm execution
     */
    public static void main(String[] args) throws Exception {
        // Load data from API
        ControlData.getInfoFromAPI();

        // Print the loaded carts
        System.out.println("CARTS LOADED:");
        for(Cart cart: ControlData.getCarts())
            System.out.println(cart);

        // Print the loaded products
        System.out.println("\nPRODUCTS LOADED:");
        for(Product product: ControlData.getProducts())
            System.out.println(product);

        // Print the loaded users
        System.out.println("\nUSERS LOADED:");
        for (User user: ControlData.getUsers())
            System.out.println(user);

        // Apply the algorithm to get total value of each product category
        System.out.println("\n\n\n1. ALL PRODUCT CATEGORIES WITH TOTAL VALUE OF PRODUCTS:");
        TotalValueCategory totalValueCat = new ProductCategories();
        totalValueCat.addProduct(ControlData.getProducts());
        System.out.println(totalValueCat.getTotalValues());

        // Apply the algorithm to get the cart with highest value and the corresponding user
        System.out.println("\n2. CART WITH HIGHEST VALUE, AND USER:");
        HighestValue highestCartAlgo = new CartHighestValue();
        HighestValue.HighestValueUser highestCart = highestCartAlgo.findUser(
                ControlData.getProducts(), ControlData.getCarts(), ControlData.getUsers()
        );
        System.out.println("User. " + highestCart.user());
        System.out.println("Value: " + highestCart.value());

        // Apply the algorithm to get the two users living furthest away from each other
        System.out.println("\n3. THE TWO USERS LIVING FURTHEST AWAY FROM EACH OTHER:");
        FurthestLiving furthestLiving = new UsersFurthestLiving();
        FurthestLiving.FurthestUsers furthestUsers = furthestLiving.findFurthestLivingUsers(ControlData.getUsers());
        System.out.println("User1. " + furthestUsers.user1());
        System.out.println("User2. " + furthestUsers.user2());
        System.out.println("Distance: " + furthestUsers.distance());
    }
}
