package algorithmes;

import algorithmes.interfaces.TotalValueCategory;
import data.Product;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Implementation of algorithm, which finds the total value of all products for each category.
 * The algorithm passes through an array with {@link Product} objects,
 * where it reads information about the category from each,
 * and summarize the price with the one already available in the HashMap {@link }
 */
public class ProductCategories implements TotalValueCategory {
    /**
     * The map that stores the total values of products for each category.
     */
    private final Map<String, Double> totalValues;
    /**
     * The decimal format used to limit the double values to two decimal places.
     */
    public static final DecimalFormat df = new DecimalFormat("#.##");
    static { df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US)); }

    /**
     * Constructs a new {@code ProductCategories} object with an empty HashMap for storing
     * the total values of products for each category.
     */
    public ProductCategories() {
        this.totalValues = new HashMap<>();
    }


    /**
     * Adds an array of {@link Product} objects to the map of total values by calling
     * {@link #addProduct(Product)} for each product in the array.
     *
     * @param products the array of {@code Product} objects to be added
     */
    @Override
    public void addProduct(Product[] products) {
        for(Product product: products)
            addProduct(product);
    }

    /**
     * Adds a {@link Product} object to the map of total values by merging its price with the
     * value already associated with the product's category.
     *
     * @param product the {@code Product} object to be added
     */
    @Override
    public void addProduct(Product product) {
        this.totalValues.merge(product.category(), product.price(), (a, b) -> Double.parseDouble(df.format(a + b)));
    }

    /**
     * Returns a new {@link HashMap} containing the total values of products for each category.
     *
     * @return a new {@code HashMap} containing the total values of products for each category
     */
    @Override
    public Map<String, Double> getTotalValues() {
        return new HashMap<>(totalValues);
    }
}
