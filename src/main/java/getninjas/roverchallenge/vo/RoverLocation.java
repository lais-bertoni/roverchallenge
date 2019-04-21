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

    public Integer getY() {
        return y;
    }

    public RoverOrientation getOrientation() {
        return orientation;
    }

    @Override
    public String toString() {
        return "RoverLocation{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", orientation='" + orientation + '\'' +
                '}';
    }
}
