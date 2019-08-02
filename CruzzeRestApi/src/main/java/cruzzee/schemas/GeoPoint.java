package cruzzee.schemas;

public class GeoPoint {
    private final double xCoordinate;
    private final double yCoordinate;

    public GeoPoint(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }
}
