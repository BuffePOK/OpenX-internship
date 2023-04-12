package data.parts.user;

import data.ConvertFromJSONException;
import org.json.JSONObject;

/**
 Represents data for a geolocation (longitude and latitude).
 */
public record GeolocationData(double latitude, double longitude) {
    /**
     * Converts a JSONObject to a GeolocationData object.
     *
     * @param object the JSONObject to be converted.
     * @return a GeolocationData object.
     * @throws ConvertFromJSONException if there is an error while converting the JSONObject.
     */
    public static GeolocationData fromJSON(JSONObject object) throws ConvertFromJSONException {
        try {
            double latitude = Double.parseDouble(object.getString("lat"));
            double longitude = Double.parseDouble(object.getString("long"));

            return new GeolocationData(latitude, longitude);

        } catch (org.json.JSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts a GeolocationData object to a JSONObject.
     *
     * @return a JSONObject representing the GeolocationData object.
     */
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("lat", String.valueOf(this.latitude));
        object.put("long", String.valueOf(this.longitude));

        return object;
    }
}
