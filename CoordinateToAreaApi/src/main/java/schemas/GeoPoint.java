package schemas;

import java.util.ArrayList;
import java.util.List;

public class GeoPoint {
    private final double latitude;
    private final double longitude;

    public GeoPoint(double xCoordinate, double yCoordinate) {
        this.latitude = xCoordinate;
        this.longitude = yCoordinate;
    }

    public double getXCoordinate(){
        return latitude;
    }

    public double getYCoordinate(){
        return longitude;
    }

    public static List<GeoPoint> getPointsFromCoordinates(List<Double> coordinates){
        List<GeoPoint> geoPoints = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i+=2) {
            geoPoints.add(new GeoPoint(coordinates.get(i), coordinates.get(i+1)));
        }
        return geoPoints;
    }
}
