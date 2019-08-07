package cruzzee.schemas;

import java.util.ArrayList;
import java.util.List;

public class GeoPoint {
    private double latitude;
    private double longitude;

    public GeoPoint() {

    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public GeoPoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static List<GeoPoint> getPointsFromCoordinates(List<Double> coordinates) {
        List<GeoPoint> geoPoints = new ArrayList<>();
        for (int i = 0; i < coordinates.size(); i += 2) {
            geoPoints.add(new GeoPoint(coordinates.get(i), coordinates.get(i + 1)));
        }
        return geoPoints;
    }
}
