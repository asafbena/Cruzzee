package schemas;

import java.util.ArrayList;
import java.util.List;

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

    public static List<GeoPoint> getPointsFromCoordinates(List<Double> coordinates){
        List<GeoPoint> geoPoints = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i+=2) {
            geoPoints.add(new GeoPoint(coordinates.get(i), coordinates.get(i+1)));
        }
        return geoPoints;
    }
}
