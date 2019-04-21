package getninjas.roverchallenge.vo;

/**
 * Represents the grid of the Mars plateau
 */
public class PlateauGrid {

    /**
     * The upper-right X coordinate of the plateau
     */
    private int upperRightX;

    /**
     * The upper-right Y coordinate of the plateau
     */
    private int upperRightY;

    public PlateauGrid(int upperRightX, int upperRightY) {
        this.upperRightX = upperRightX;
        this.upperRightY = upperRightY;
    }

    public int getUpperRightX() {
        return upperRightX;
    }

    public int getUpperRightY() {
        return upperRightY;
    }



    @Override
    public String toString() {
        return "PlateauGrid{" +
                "upperRightX=" + upperRightX +
                ", upperRightY=" + upperRightY +
                '}';
    }
}
