package data;

import java.io.IOException;

    /**
     * Exception occur when converting JSON data to Java objects
     */
public class ConvertFromJSONException extends IOException {
        /**
         * The constructor.
         *
         * @param e String, which represents the error message to be thrown when the exception occurs
         */
    public ConvertFromJSONException(String e) {
        super(e);
    }
}
