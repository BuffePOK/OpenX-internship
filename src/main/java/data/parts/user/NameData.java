package data.parts.user;

import data.ConvertFromJSONException;
import org.json.JSONObject;

/**
 Represents data for a person(firstname and lastname).
 */
public record NameData(String firstname, String lastname) {
    /**
     * Converts a JSONObject to a NameData object.
     *
     * @param object the JSONObject to be converted.
     * @return a NameData object.
     * @throws ConvertFromJSONException if there is an error while converting the JSONObject.
     */
    public static NameData fromJSON(JSONObject object) throws ConvertFromJSONException {
        try {
            String firstname = object.getString("firstname");
            String lastname = object.getString("lastname");

            return new NameData(firstname, lastname);

        } catch (org.json.JSONException e) {
            throw new ConvertFromJSONException(e.getMessage());
        }
    }

    /**
     * Converts a NameData object to a JSONObject.
     *
     * @return a JSONObject representing the NameData object.
     */
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();

        object.put("firstname", this.firstname);
        object.put("lastname", this.lastname);

        return object;
    }
}
