package getninjas.roverchallenge.util;

import getninjas.roverchallenge.exceptions.InvalidRoverCommandException;
import getninjas.roverchallenge.exceptions.InvalidRoverOrientationException;
import getninjas.roverchallenge.vo.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class EntryUtil {

    private EntryUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Reads the file on the given path and populates a value object with its content
     *
     * @param entryFilePath The path of the entry file
     * @return The VO with the entry information
     */
    public static RoverChallenge readEntryFile(String entryFilePath) {

        try (BufferedReader br = Files.newBufferedReader(Paths.get(entryFilePath))) {

            PlateauGrid plateauGrid = readPlateauGridUpperRightCoordinates(br);

            List<RoverInfo> roverInfos = readRoverInformations(br);

            return new RoverChallenge(plateauGrid, roverInfos);

        } catch (IOException e) {
            System.err.println("Invalid entry file path.");
        } catch (NumberFormatException e) {
            System.err.println("Invalid entry file content.");
        } catch (StringIndexOutOfBoundsException e) {
            System.err.println("Missing information in the entry file.");
        } catch (InvalidRoverOrientationException e) {
            System.err.println("Invalid orientation. " + e.getMessage());
        } catch (InvalidRoverCommandException e) {
            System.err.println("Invalid command. " + e.getMessage());
        }

        return null;
    }

    /**
     * Reads the rover informations from the entry file.
     *
     * @param br The BufferedReader of the entry file
     * @return A list containing @RoverInfo value objects
     * @throws IOException                      If there is a problem reading the file
     * @throws InvalidRoverOrientationException If the given rover orientation is not an expected one
     * @throws InvalidRoverCommandException     If the given rover command is not an expected one
     */
    private static List<RoverInfo> readRoverInformations(BufferedReader br) throws IOException, InvalidRoverOrientationException, InvalidRoverCommandException {
        String line;
        List<RoverInfo> roverInfos = new LinkedList<>();

        while ((line = br.readLine()) != null) {
            RoverLocation roverLocation = readRoverLocation(line);
            LinkedList<RoverCommand> commands = readRoverCommands(br);
            roverInfos.add(new RoverInfo(roverLocation, commands));
        }

        return roverInfos;
    }

    /**
     * Reads the rover locations from the entry file
     *
     * @param line The line containing the rover location data
     * @return A @{@link RoverLocation} value object
     * @throws InvalidRoverOrientationException If the given rover orientation is not an expected one
     */
    private static RoverLocation readRoverLocation(String line) throws InvalidRoverOrientationException {
        int roverX = Integer.parseInt(line.substring(0, 1));
        int roverY = Integer.parseInt(line.substring(2, 3));
        String roverOrientation = line.substring(4, 5);

        return new RoverLocation(roverX, roverY, RoverOrientation.fromValue(roverOrientation));

    }

    /**
     * Reads the rover commands from the entry file
     *
     * @param br The BufferedReader of the entry file
     * @return A list containing @{@link RoverCommand} value objects
     * @throws IOException                  If there is a problem reading the file
     * @throws InvalidRoverCommandException If the given rover command is not an expected one
     */
    private static LinkedList<RoverCommand> readRoverCommands(BufferedReader br) throws IOException, InvalidRoverCommandException {
        String line;
        LinkedList<RoverCommand> commands = new LinkedList<>();
        if ((line = br.readLine()) != null) {

            String[] roverCommands = line.split("");

            for (String c : roverCommands) {
                commands.add(RoverCommand.fromValue(c));
            }
        }

        return commands;
    }

    /**
     * Reads the Mars plateau coordinates from the entry file
     *
     * @param br The BufferedReader of the entry file
     * @return A @{@link PlateauGrid} value object
     * @throws IOException If there is a problem reading the file
     */
    private static PlateauGrid readPlateauGridUpperRightCoordinates(BufferedReader br) throws IOException {
        String line;
        PlateauGrid plateauGrid = null;
        if ((line = br.readLine()) != null) {

            int upperRightX = Integer.parseInt(line.substring(0, 1));
            int upperRightY = Integer.parseInt(line.substring(2, 3));

            plateauGrid = new PlateauGrid(upperRightX, upperRightY);
        }
        return plateauGrid;
    }
}
