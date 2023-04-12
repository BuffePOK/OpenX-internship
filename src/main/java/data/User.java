package data;

import data.parts.user.AddressData;
import data.parts.user.NameData;
import org.json.JSONObject;


/**
 Represents data for a User ({@link AddressData} address, id, email, username, password, {@link NameData} name, phone).
 */
public record User(AddressData address, long id, String email, String username,
                   char[] password, NameData name, String phone, int __v) {

    /**
     * Converts a JSONObject into a User object.
     *
     * @param object a JSONObject representing the User to be converted
     * @return a User object created from the JSONObject
     * @throws ConvertFromJSONException if an error occurs while parsing the JSONObject
     */
    public static User fromJSON(JSONObject object) throws ConvertFromJSONException {
        // Parse the JSONObject and create a new User object from its contents
        try {
        AddressData address = AddressData.fromJSON(object.getJSONObject("address"));
        long id = object.getLong("id");
        String email = object.getString("email");
        String username = object.getString("username");
        char[] password = object.getString("password").toCharArray();
        NameData name = NameData.fromJSON(object.getJSONObject("name"));
        String phone = object.getString("phone");
        int __v = object.getInt("__v");

        return new User(address, id, email, username, password, name, phone, __v);

        } catch (ConvertFromJSONException | org.json.JSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts the User object into a JSONObject.
     *
     * @return a JSONObject representing the User object
     */
    public JSONObject toJSON() {
        // Create a new JSONObject and fill it with the User object's properties
        JSONObject object = new JSONObject();

        object.put("address", this.address.toJSON());
        object.put("id", this.id);
        object.put("email", this.email);
        object.put("username", this.username);
        object.put("password", String.valueOf(this.password));
        object.put("name", this.name.toJSON());
        object.put("phone", this.phone);
        object.put("__v", this.__v);

        return object;
    }

    /**
     * Returns a pretty-printed string representation of the User object as a JSON object.
     *
     * @return a formatted string representing the User object as a JSON object
     */
    @Override
    public String toString() {
        return "ID: " + this.id +
                ". Username: " + this.username +
                ". Full name: " + this.name.firstname() + " " + this.name.lastname() +
                ". Geolocation. Longitude: " + this.address.geolocation().longitude() +
                ". Latitude: " + this.address.geolocation().latitude();
    }
}
