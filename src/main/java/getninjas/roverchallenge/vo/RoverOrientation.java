package getninjas.roverchallenge.vo;

import getninjas.roverchallenge.exceptions.InvalidRoverOrientationException;

import java.util.Arrays;

/**
 * Represents the direction the rover is facing
 */
public enum RoverOrientation {

    NORTH("N"),
    EAST("E"),
    WEST("W"),
    SOUTH("S");

    private final String value;

    RoverOrientation(String value) {
        this.value = value;
    }

    /**
     * Finds the RoverOrientation of the given value.
     * Example:
     * If the value is 'W' returns WEST
     *
     * @param value The character representing the rover orientation
     * @return @{@link RoverOrientation}
     * @throws InvalidRoverOrientationException if there is no @{@link RoverOrientation} compatible with the given value
     */
    public static RoverOrientation fromValue(String value) throws InvalidRoverOrientationException {
        return Arrays.stream(RoverOrientation.values())
                .filter(ro -> ro.value.equals(value))
                .findFirst()
                .orElseThrow(() -> new InvalidRoverOrientationException(value));
    }
}
