package spaceX.exception;

public class SpaceException extends RuntimeException {

    public SpaceException(String message) {
        super(message);
    }

    public void logMessage() {
        System.out.println(getMessage());
    }
}
