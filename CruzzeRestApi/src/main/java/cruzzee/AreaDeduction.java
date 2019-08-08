package cruzzee;

import cruzzee.schemas.GeoPoint;
import java.util.List;

public class AreaDeduction {

    private static double getAverage(double value1, double value2) {
        return (value1 + value2) / 2;
    }

    public static GeoPoint getMiddlePoint(GeoPoint point1, GeoPoint point2) {
        return new GeoPoint(
                getAverage(point1.getLatitude(), point2.getLatitude()),
                getAverage(point1.getLongitude(), point2.getLongitude())
        );
    }

    private static List<GeoPoint> getPointsStream() {
        return null;
    }
}