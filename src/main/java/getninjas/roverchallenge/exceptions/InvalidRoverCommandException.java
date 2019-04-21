package getninjas.roverchallenge.exceptions;

public class InvalidRoverCommandException extends Exception {

    public InvalidRoverCommandException(String command) {
        super(command + " is not a valid command.");
    }
}
