package data;

import data.parts.product.RatingData;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 Represents data for a Product (id, title, price, description, category, image, {@link RatingData} rating).
 */
public record Product(long id, String title, double price, String description, String category,
                      URL image, RatingData rating) {

    /**
     * Converts a JSONObject into a Product object.
     *
     * @param object a JSONObject representing the Product to be converted
     * @return a Product object created from the JSONObject
     * @throws ConvertFromJSONException if an error occurs while parsing the JSONObject
     */
    public static Product fromJSON(JSONObject object) throws ConvertFromJSONException {
        try {
            long id = object.getLong("id");
            String title = object.getString("title");
            double price = object.getDouble("price");
            String description = object.getString("description");
            String category = object.getString("category");
            URL image = new URL(object.getString("image"));
            RatingData rating = RatingData.fromJSON(object.getJSONObject("rating"));

            return new Product(id, title, price, description, category, image, rating);

        } catch (MalformedURLException | org.json.JSONException | ConvertFromJSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts the Product object into a JSONObject.
     *
     * @return a JSONObject representing the Product object
     */
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("id", this.id);
        object.put("title", this.title);
        object.put("price", this.price);
        object.put("description", this.description);
        object.put("category", this.category);
        object.put("image", this.image.toString());
        object.put("rating", this.rating.toJSON());

        return object;
    }

    /**
     * Returns a pretty-printed string representation of the Product object as a JSON object.
     *
     * @return a formatted string representing the Product object as a JSON object
     */
    @Override
    public String toString() {
        return "ID: " + this.id +
                ". Title: " + this.title +
                ". Price: " + this.price;
    }
}
