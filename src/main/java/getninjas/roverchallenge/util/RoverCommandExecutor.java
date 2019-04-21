package getninjas.roverchallenge.util;

import getninjas.roverchallenge.vo.*;

public class RoverCommandExecutor {

    public static void executeCommands(final RoverChallenge roverChallenge) {

        for (RoverInfo roverInfo : roverChallenge.getRoverInfos()) {

            final RoverLocation actualLocation = roverInfo.getLocation();

            for (RoverCommand command : roverInfo.getCommands()) {
                executeCommand(actualLocation, command, roverChallenge);
            }

            System.out.println(actualLocation);
        }
    }

    private static void executeCommand(RoverLocation actualLocation, final RoverCommand command, final RoverChallenge roverChallenge) {

        final PlateauGrid plateauGrid = roverChallenge.getPlateauGrid();

        if (command.equals(RoverCommand.MOVE)) {
            actualLocation.move(plateauGrid);
        } else {
            final RoverOrientation orientationAfterCommand = actualLocation.getOrientation().getAfterCommand(command);
            actualLocation.setOrientation(orientationAfterCommand);
        }

    }
}
