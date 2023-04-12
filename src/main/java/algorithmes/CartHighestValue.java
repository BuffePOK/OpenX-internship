package algorithmes;

import algorithmes.interfaces.HighestValue;
import data.Cart;
import data.Product;
import data.User;
import data.parts.cart.ProductsData;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of algorithm, which finds the user who has the highest value cart.
 * 1. We put all the products we know in a HashMap in order to quickly get information about them when we need it.
 * 2. We consider the information about each cart known to us, where we summarize prices about each product, using HashMap (1).
 * 3. We have information about the most expensive cart, as well as the user's lunch.
 *    Now we are looking for the right user by going through the array.
 */
public class CartHighestValue implements HighestValue {
    @Override
    public HighestValueUser findUser(Product[] products, Cart[] carts, User[] users) {
        Map<Long, Product> IDsProducts = new HashMap<>();
        for(Product product: products) // O(n)
            IDsProducts.put(product.id(), product);

        StepCart stepCart = new StepCart(-1, -1.0f);
        for(Cart cart: carts) { // O(n)
            double totalPrice = 0.0f;
            for(ProductsData productsData: cart.products()) { // O(n)
                totalPrice += ( IDsProducts.get(productsData.productId()).price() * productsData.quantity() );
            }
            if(stepCart.totalPrice() < totalPrice)
                stepCart = new StepCart(cart.userId(), totalPrice);
        }

        for(User user: users) // O(n)
            if(user.id() == stepCart.id())
                return new HighestValueUser(user, stepCart.totalPrice());

        return null;
    }
}

record StepCart(long id, double totalPrice) {}