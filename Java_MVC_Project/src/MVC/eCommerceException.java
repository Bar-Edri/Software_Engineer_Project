package MVC;

/**
 * this class will represent all the error messages of the project.
 */
public class eCommerceException extends Exception {

    public eCommerceException(String message) {
        super(message);
    }

    public eCommerceException(String message, Throwable cause) {
        super(message, cause);
    }
}
