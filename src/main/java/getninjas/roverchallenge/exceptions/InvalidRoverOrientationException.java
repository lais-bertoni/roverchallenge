package getninjas.roverchallenge.exceptions;

public class InvalidRoverOrientationException extends Exception {

    public InvalidRoverOrientationException(String orientation) {
        super(orientation + " is not a valid orientation.");
    }
}
