package algorithmes.interfaces;

import data.Cart;
import data.Product;
import data.User;

/**
 * Interface of algorithm, which finds the user who has the highest value cart.
 */
public interface HighestValue {
    /**
     * Finds the user who has the highest value cart among the given arrays of products, carts and users.
     * The time complexity of the algorithm is: O(2n) ///////////////////////////////////////////////////////////////////
     *          For {@link Product} objects - O(n)
     *          For {@link Cart} objects - O(n) +
     *              For {@link data.parts.cart.ProductsData} objects - O(n)
     *          For {@link User} objects - O(n)
     * Of course, we can immediately accumulate this data in sorted form,
     * and then the speed of the algorithm will increase.
     * However, it is not known how often we will use this algorithm in the case,
     * which is why it is unclear whether this decision makes sense.
     *
     * @param products an array of {@link Product} objects representing the products in system.
     * @param carts an array of {@link Cart} objects representing the carts to search for
     * @param users an array of {@link User} objects representing the users who own the carts
     * @return a HighestValueUser object containing the User object who owns the highest value cart, and the total value of that cart
     */
    HighestValueUser findUser(Product[] products, Cart[] carts, User[] users);

    /**
     * A simple record to store the {@link User} object who owns the highest value cart and the total value of that cart.
     */
    record HighestValueUser(User user, double value) {};
}
