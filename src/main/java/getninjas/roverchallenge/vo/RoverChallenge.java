package getninjas.roverchallenge.vo;

import java.util.List;

/**
 * Represents all of the entry informations of the rover challenge
 */
public class RoverChallenge {

    /**
     * The grid of the Mars plateau
     */
    private PlateauGrid plateauGrid;

    /**
     * The data of the rovers locations and commands
     */
    private List<RoverInfo> roverInfos;

    public RoverChallenge(PlateauGrid plateauGrid, List<RoverInfo> roverInfos) {
        this.plateauGrid = plateauGrid;
        this.roverInfos = roverInfos;
    }

    public PlateauGrid getPlateauGrid() {
        return plateauGrid;
    }

    public List<RoverInfo> getRoverInfos() {
        return roverInfos;
    }

    @Override
    public String toString() {
        return "RoverChallenge{" +
                "plateauGrid=" + plateauGrid +
                ", roverInfos=" + roverInfos +
                '}';
    }
}
