package data.parts.user;

import data.ConvertFromJSONException;
import org.json.JSONObject;

/**
 Represents data for an address (city, street, number), {@link GeolocationData} geolocation, zipcode.
 */
public record AddressData(GeolocationData geolocation, String city, String street,
                          int number, String zipcode) {
    /**
     * Converts a JSONObject to a AddressData object.
     *
     * @param object the JSONObject to be converted.
     * @return a AddressData object.
     * @throws ConvertFromJSONException if there is an error while converting the JSONObject.
     */
    public static AddressData fromJSON(JSONObject object) throws ConvertFromJSONException {
        try {
            GeolocationData geolocation = GeolocationData.fromJSON(object.getJSONObject("geolocation"));
            String city = object.getString("city");
            String street = object.getString("street");
            int number = object.getInt("number");
            String zipcode = object.getString("zipcode");

            return new AddressData(geolocation, city, street, number, zipcode);

        } catch (org.json.JSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts a AddressData object to a JSONObject.
     *
     * @return a JSONObject representing the AddressData object.
     */
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("geolocation", this.geolocation.toJSON());
        object.put("city", this.city);
        object.put("street", this.street);
        object.put("number", this.number);
        object.put("zipcode", this.zipcode);

        return object;
    }
}
