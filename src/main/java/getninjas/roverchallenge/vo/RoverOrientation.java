package getninjas.roverchallenge.vo;

import getninjas.roverchallenge.exceptions.InvalidRoverOrientationException;

import java.util.Arrays;

/**
 * Represents the direction the rover is facing
 */
public enum RoverOrientation {

    NORTH("N", "W", "E"),
    EAST("E", "N", "S"),
    WEST("W", "S", "N"),
    SOUTH("S", "E", "W");

    private final String value;
    private final String afterLeftCommand;
    private final String afterRightCommand;

    RoverOrientation(String value, String afterLeftCommand, String afterRightCommand) {
        this.value = value;
        this.afterLeftCommand = afterLeftCommand;
        this.afterRightCommand = afterRightCommand;
    }

    public String getValue() {
        return this.value;
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

    /**
     * Returns the rover orientation after a command is executed
     * @param command The command that should be executed
     * @return the @{@link RoverOrientation} after a command is executed
     */
    public RoverOrientation getAfterCommand(final RoverCommand command) {
        try {
            if (command.equals(RoverCommand.LEFT)) {
                return fromValue(afterLeftCommand);
            } else if (command.equals(RoverCommand.RIGHT)) {
                return fromValue(afterRightCommand);
            } else {
                return this;
            }
        } catch (InvalidRoverOrientationException e) {
            System.err.println("Invalid orientation.");
        }

        return null;
    }
}
