package getninjas.roverchallenge.vo;

import java.util.LinkedList;

/**
 * Represents all the information about a rover.
 */
public class RoverInfo {

    private RoverLocation location;

    /**
     * Commands given to the rover.
     * <p>
     * I chose the LinkedList because I want to keep the insertion order of the commands.
     */
    private LinkedList<RoverCommand> commands;

    public RoverInfo(RoverLocation location, LinkedList<RoverCommand> commands) {
        this.location = location;
        this.commands = commands;
    }

    public RoverLocation getLocation() {
        return location;
    }

    public LinkedList<RoverCommand> getCommands() {
        return commands;
    }

    @Override
    public String toString() {
        return "RoverInfo{" +
                "location=" + location +
                ", commands=" + commands +
                '}';
    }
}
