package data;

import data.parts.cart.ProductsData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 Represents data for a Cart (id, userId, {@link LocalDateTime} date, {@link ProductsData} products).
 */
public record Cart(long id, long userId, LocalDateTime date, ProductsData[] products, int __v) {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    /**
     * Converts a JSONObject into a Cart object.
     *
     * @param object a JSONObject representing the Cart to be converted
     * @return a Cart object created from the JSONObject
     * @throws ConvertFromJSONException if an error occurs while parsing the JSONObject
     */
    public static Cart fromJSON(JSONObject object) throws ConvertFromJSONException {
        try {
            long id = object.getLong("id");
            long userId = object.getLong("userId");
            LocalDateTime date = LocalDateTime.parse(object.getString("date"), formatter);
            ProductsData[] products = ProductsData.fromJSON(object.getJSONArray("products"));
            int __v = object.getInt("__v");

            return new Cart(id, userId, date, products, __v);

        } catch (ConvertFromJSONException | org.json.JSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts the Cart object into a JSONObject.
     *
     * @return a JSONObject representing the Cart object
     */
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("userId", this.userId);
        object.put("date", this.date.format(formatter));

        JSONArray array = new JSONArray();
        for(ProductsData productsData:this.products)
            array.put(productsData.toJSON());
        object.put("products", array);

        object.put("__v", this.__v);

        return object;
    }

    /**
     * Returns a pretty-printed string representation of the Cart object as a JSON object.
     *
     * @return a formatted string representing the Cart object as a JSON object
     */
    @Override
    public String toString() {
        return "ID: " + this.id +
                ". UserID: " + this.userId +
                ". Products: " + this.products.length +
                ". Date: " + date.format(formatter);
    }
}
