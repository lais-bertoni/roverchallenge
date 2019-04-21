package getninjas.roverchallenge.vo;

import getninjas.roverchallenge.exceptions.InvalidRoverCommandException;

import java.util.Arrays;

/**
 * Represents the command the rover can be given
 */
public enum RoverCommand {

    //The rover spins 90 degrees to the left
    LEFT("L"),

    //The rover spins 90 degrees to the right
    RIGHT("R"),

    //The rover moves forward
    MOVE("M");

    private final String value;

    RoverCommand(String value) {
        this.value = value;
    }

    /**
     * Finds the RoverCommand of the given value.
     * Example:
     * If the value is 'R' returns RIGHT
     *
     * @param value The character representing the rover command
     * @return @{@link RoverCommand}
     * @throws InvalidRoverCommandException if there is no @{@link RoverCommand} compatible with the given value
     */
    public static RoverCommand fromValue(String value) throws InvalidRoverCommandException {
        return Arrays.stream(RoverCommand.values())
                .filter(ro -> ro.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new InvalidRoverCommandException(value));
    }
}
