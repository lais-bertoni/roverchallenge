package getninjas.roverchallenge.vo;

/**
 * Represents the rover location on the mars plateau
 */
public class RoverLocation {

    /**
     * X coordinate of the rover location
     */
    private Integer x;

    /**
     * Y coordinate of the rover location
     */
    private Integer y;

    /**
     * Orientation of the rover.
     * The direction that the rover is facing.
     */
    private RoverOrientation orientation;

    public RoverLocation(Integer x, Integer y, RoverOrientation position) {
        this.x = x;
        this.y = y;
        this.orientation = position;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public RoverOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(RoverOrientation orientation) {
        this.orientation = orientation;
    }

    /**
     * Updates the rover location with the given orientation.
     * If the rover is in the border of the plateau, the location is not updated.
     * @param plateauGrid The Mars plateau grid coordinates.
     */
    public void move(final PlateauGrid plateauGrid) {
        if (this.getOrientation().equals(RoverOrientation.NORTH)) {
            if (this.getY() < plateauGrid.getUpperRightY()) {
                this.setY(this.getY() + 1);
            }
        } else if (this.getOrientation().equals(RoverOrientation.SOUTH)) {
            if (this.getY() > 0) {
                this.setY(this.getY() - 1);
            }
        } else if (this.getOrientation().equals(RoverOrientation.EAST)) {
            if (this.getX() < plateauGrid.getUpperRightX()) {
                this.setX(this.getX() + 1);
            }
        } else if (this.getOrientation().equals(RoverOrientation.WEST)) {
            if (this.getX() > 0) {
                this.setX(this.getX() - 1);
            }
        }
    }

    @Override
    public String toString() {
        return x + " " + y + " " + orientation.getValue();
    }
}
