package getninjas.roverchallenge;

import getninjas.roverchallenge.util.EntryUtil;
import getninjas.roverchallenge.util.RoverCommandExecutor;
import getninjas.roverchallenge.vo.RoverChallenge;

import java.util.Scanner;

public class RoverchallengeApplication {

    public static void main(String[] args) {

        String entryFilePath = "./src/main/resources/";

        System.out.println("Insert your file name.");
        Scanner scanner = new Scanner(System.in);

        String fileName = scanner.nextLine();

        RoverChallenge roverChallenge = EntryUtil.readEntryFile(entryFilePath + fileName);

        if (roverChallenge != null) {
            RoverCommandExecutor.executeCommands(roverChallenge);
        }
    }
}