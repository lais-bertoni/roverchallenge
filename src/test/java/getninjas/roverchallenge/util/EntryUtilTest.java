package getninjas.roverchallenge.util;

import getninjas.roverchallenge.vo.RoverChallenge;
import getninjas.roverchallenge.vo.RoverCommand;
import getninjas.roverchallenge.vo.RoverInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static getninjas.roverchallenge.vo.RoverCommand.*;
import static getninjas.roverchallenge.vo.RoverOrientation.EAST;
import static getninjas.roverchallenge.vo.RoverOrientation.NORTH;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class EntryUtilTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void validEntryFile() {
        final String entryFilePath = "./src/test/resources/valid_entry.txt";

        final RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        assertThat(roverChallenge).isNotNull();

        assertPlateauGrid(roverChallenge);

        assertRovers(roverChallenge);
    }

    @Test
    public void invalidEntryFilePath() {
        final String entryFilePath = "./src/test/resources/valid_entry123.txt";

        final RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        assertThat(roverChallenge).isNull();

        assertThat(errContent.toString()).isEqualTo("Invalid entry file path.\r\n");
    }

    @Test
    public void invalidEntryFileContent() {
        final String entryFilePath = "./src/test/resources/invalid_content_entry.txt";

        final RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        assertThat(roverChallenge).isNull();

        assertThat(errContent.toString()).isEqualTo("Invalid entry file content.\r\n");
    }

    @Test
    public void invalidEntryFileMissingInformation() {
        final String entryFilePath = "./src/test/resources/invalid_missing_info_entry.txt";

        final RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        assertThat(roverChallenge).isNull();

        assertThat(errContent.toString()).isEqualTo("Missing information in the entry file.\r\n");
    }

    @Test
    public void invalidEntryInvalidRoverOrientation() {
        final String entryFilePath = "./src/test/resources/invalid_rover_orientation_entry.txt";

        final RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        assertThat(roverChallenge).isNull();

        assertThat(errContent.toString()).contains("Invalid orientation.");
    }

    @Test
    public void invalidEntryInvalidRoverCommand() {
        final String entryFilePath = "./src/test/resources/invalid_rover_command_entry.txt";

        final RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        assertThat(roverChallenge).isNull();

        assertThat(errContent.toString()).contains("Invalid command.");
    }

    private void assertRovers(RoverChallenge roverChallenge) {
        assertThat(roverChallenge.getRoverInfos()).isNotNull();
        assertThat(roverChallenge.getRoverInfos().size()).isEqualTo(2);

        assertFirstRoverInfo(roverChallenge);

        assertSecondRoverInfo(roverChallenge);
    }

    private void assertSecondRoverInfo(RoverChallenge roverChallenge) {
        final RoverInfo secondRover = roverChallenge.getRoverInfos().get(1);

        assertThat(secondRover).isNotNull();

        assertSecondRoverLocations(secondRover);

        assertSecondRoverCommands(secondRover);
    }

    private void assertSecondRoverCommands(RoverInfo secondRover) {
        final LinkedList<RoverCommand> secondRoverCommands = secondRover.getCommands();

        assertThat(secondRoverCommands).isNotNull();

        assertThat(secondRoverCommands.size()).isEqualTo(10);

        assertThat(secondRoverCommands.get(0)).isEqualTo(MOVE);
        assertThat(secondRoverCommands.get(1)).isEqualTo(MOVE);
        assertThat(secondRoverCommands.get(2)).isEqualTo(RIGHT);
        assertThat(secondRoverCommands.get(3)).isEqualTo(MOVE);
        assertThat(secondRoverCommands.get(4)).isEqualTo(MOVE);
        assertThat(secondRoverCommands.get(5)).isEqualTo(RIGHT);
        assertThat(secondRoverCommands.get(6)).isEqualTo(MOVE);
        assertThat(secondRoverCommands.get(7)).isEqualTo(RIGHT);
        assertThat(secondRoverCommands.get(8)).isEqualTo(RIGHT);
        assertThat(secondRoverCommands.get(9)).isEqualTo(MOVE);
    }

    private void assertSecondRoverLocations(RoverInfo secondRover) {
        assertThat(secondRover.getLocation()).isNotNull();

        assertThat(secondRover.getLocation().getX()).isEqualTo(3);

        assertThat(secondRover.getLocation().getY()).isEqualTo(3);

        assertThat(secondRover.getLocation().getOrientation()).isEqualTo(EAST);
    }

    private void assertFirstRoverInfo(RoverChallenge roverChallenge) {
        final RoverInfo firstRover = roverChallenge.getRoverInfos().get(0);

        assertThat(firstRover).isNotNull();

        assertFirstRoverLocations(firstRover);

        assertFirstRoverCommands(firstRover);
    }

    private void assertFirstRoverCommands(RoverInfo firstRover) {
        final LinkedList<RoverCommand> firstRoverCommands = firstRover.getCommands();

        assertThat(firstRoverCommands).isNotNull();

        assertThat(firstRoverCommands.size()).isEqualTo(9);

        assertThat(firstRoverCommands.get(0)).isEqualTo(LEFT);
        assertThat(firstRoverCommands.get(1)).isEqualTo(MOVE);
        assertThat(firstRoverCommands.get(2)).isEqualTo(LEFT);
        assertThat(firstRoverCommands.get(3)).isEqualTo(MOVE);
        assertThat(firstRoverCommands.get(4)).isEqualTo(LEFT);
        assertThat(firstRoverCommands.get(5)).isEqualTo(MOVE);
        assertThat(firstRoverCommands.get(6)).isEqualTo(LEFT);
        assertThat(firstRoverCommands.get(7)).isEqualTo(MOVE);
        assertThat(firstRoverCommands.get(8)).isEqualTo(MOVE);
    }

    private void assertFirstRoverLocations(RoverInfo firstRover) {
        assertThat(firstRover.getLocation()).isNotNull();

        assertThat(firstRover.getLocation().getX()).isEqualTo(1);

        assertThat(firstRover.getLocation().getY()).isEqualTo(2);

        assertThat(firstRover.getLocation().getOrientation()).isEqualTo(NORTH);
    }

    private void assertPlateauGrid(RoverChallenge roverChallenge) {
        assertThat(roverChallenge.getPlateauGrid()).isNotNull();

        assertThat(roverChallenge.getPlateauGrid().getUpperRightY()).isNotNull().isEqualTo(5);

        assertThat(roverChallenge.getPlateauGrid().getUpperRightY()).isNotNull().isEqualTo(5);
    }

}