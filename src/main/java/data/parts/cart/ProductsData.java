package data.parts.cart;

import data.ConvertFromJSONException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 Represents data for a product and its quantity.
 */
public record ProductsData(long productId, int quantity) {
    /**
     * Converts a JSONObject to a ProductsData object.
     *
     * @param object the JSONObject to be converted.
     * @return a ProductsData object.
     * @throws ConvertFromJSONException if there is an error while converting the JSONObject.
     */
    public static ProductsData fromJSON(JSONObject object) throws ConvertFromJSONException {
        try {
            long productId = object.getLong("productId");
            int quantity = object.getInt("quantity");

            return new ProductsData(productId, quantity);

        } catch (org.json.JSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts a JSONArray to an array of ProductsData objects.
     *
     * @param array the JSONArray to be converted.
     * @return an array of ProductsData objects.
     * @throws ConvertFromJSONException if there is an error while converting the JSONArray.
     */
    public static ProductsData[] fromJSON(JSONArray array) throws ConvertFromJSONException {
        try {
            ProductsData[] products = new ProductsData[array.length()];
            for(int i = 0; i < array.length(); i++)
                products[i] = fromJSON(array.getJSONObject(i));

            return products;

        } catch (org.json.JSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts a ProductsData object to a JSONObject.
     *
     * @return a JSONObject representing the ProductsData object.
     */
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("productId", this.productId);
        object.put("quantity", this.quantity);

        return object;
    }
}
