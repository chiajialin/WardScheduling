package wardscheduling;

/**
 * Custom checked exception.
 * Thrown by the controller's addRequest(), caught in hour(): reject + count + carry on.
 */
public class PlaceNotFoundException extends Exception {

    public PlaceNotFoundException(String message) {
        super(message);
    }
}
