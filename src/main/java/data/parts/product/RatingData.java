package data.parts.product;

import data.ConvertFromJSONException;
import org.json.JSONObject;

/**
 Represents data for a rating, and it counts of voices.
 */
public record RatingData(double rate, int count) {
    /**
     * Converts a JSONObject to a RatingData object.
     *
     * @param object the JSONObject to be converted.
     * @return a RatingData object.
     * @throws ConvertFromJSONException if there is an error while converting the JSONObject.
     */
    public static RatingData fromJSON(JSONObject object) throws ConvertFromJSONException {
        try {
            double rate = object.getDouble("rate");
            int count = object.getInt("count");

            return new RatingData(rate, count);
        } catch (org.json.JSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts a RatingData object to a JSONObject.
     *
     * @return a JSONObject representing the RatingData object.
     */
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("rate", this.rate);
        object.put("count", this.count);

        return object;
    }
}
