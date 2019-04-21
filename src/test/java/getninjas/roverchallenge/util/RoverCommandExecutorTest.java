package getninjas.roverchallenge.util;

import getninjas.roverchallenge.vo.RoverChallenge;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RoverCommandExecutorTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void executeWithChallengeGivenEntrySet() {

        final String entryFilePath = "./src/test/resources/valid_entry.txt";

        RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        RoverCommandExecutor.executeCommands(roverChallenge);

        assertThat(outContent.toString()).isEqualTo(
                "1 3 N\r\n" +
                        "5 1 E\r\n");
    }

    @Test
    public void executeWithNoCommands() {

        final String entryFilePath = "./src/test/resources/valid_entry_no_commands.txt";

        RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        RoverCommandExecutor.executeCommands(roverChallenge);

        assertThat(outContent.toString()).isEqualTo("1 2 N\r\n");
    }

    @Test
    public void executeWithMoreRightMovementsThanXPlateauCoordinate() {

        final String entryFilePath = "./src/test/resources/valid_entry_right_movements.txt";

        RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        RoverCommandExecutor.executeCommands(roverChallenge);

        assertThat(outContent.toString()).isEqualTo("5 2 E\r\n");
    }

    @Test
    public void executeWithMoreLeftMovementsThanXPlateauCoordinate() {

        final String entryFilePath = "./src/test/resources/valid_entry_left_movements.txt";

        RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        RoverCommandExecutor.executeCommands(roverChallenge);

        assertThat(outContent.toString()).isEqualTo("0 2 W\r\n");
    }

    @Test
    public void executeWithMoreUpMovementsThanYPlateauCoordinate() {

        final String entryFilePath = "./src/test/resources/valid_entry_up_movements.txt";

        RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        RoverCommandExecutor.executeCommands(roverChallenge);

        assertThat(outContent.toString()).isEqualTo("1 5 N\r\n");
    }

    @Test
    public void executeWithMoreDownMovementsThanYPlateauCoordinate() {

        final String entryFilePath = "./src/test/resources/valid_entry_down_movements.txt";

        RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath);

        RoverCommandExecutor.executeCommands(roverChallenge);

        assertThat(outContent.toString()).isEqualTo("1 0 S\r\n");
    }
}