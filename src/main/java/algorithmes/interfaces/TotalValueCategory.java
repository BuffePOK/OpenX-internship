package algorithmes.interfaces;

import data.Product;

import java.util.HashMap;
import java.util.Map;

/**
 * Interface of algorithm, which finds the total value of all products for each category.
 */
public interface TotalValueCategory {
    /**
     * Computes the total value of all products for each category in the given array of products.
     * The time complexity of the algorithm is: O(n)
     *
     * @param products an array of {@link Product} objects representing the products to compute the total value for each category
     * @return a Map{@literal <}String, Double{@literal >} object representing the total value of all products for each category,
     * where the key is the category name and the value is the total value for that category
     */
    static Map<String, Double> findTotalValuesForCategories(Product[] products) {
        Map<String, Double> totalValues = new HashMap<>();

        for(Product product: products)
            totalValues.merge(product.category(), product.price(), Double::sum);

        return totalValues;
    }

    /**
     * Adding array of products, to compute total value.
     * The time complexity of the algorithm is: O(n)
     *
     * @param products an array of {@link Product} objects representing the products to compute the total value
     */
    void addProduct(Product[] products);
    /**
     * Adding array of products, to compute total value.
     * The time complexity of the algorithm is: O(1)
     *
     * @param product {@link Product} object representing the product to compute the total value
     */
    void addProduct(Product product);

    /**
     * @return a Map{@literal <}String, Double{@literal >} object representing the total value of all products for each category,
     *         where the key is the category name and the value is the total value for that category
     */
    Map<String, Double> getTotalValues();
}
