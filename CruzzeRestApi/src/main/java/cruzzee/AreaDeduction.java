package cruzzee;

import cruzzee.schemas.GeoPoint;

import java.util.List;

class AreaDeduction {

    private static double getAverage(double value1, double value2) {
        return (value1 + value2) / 2;
    }

    public static GeoPoint getMiddlePoint(GeoPoint point1, GeoPoint point2) {
        return new GeoPoint(
                getAverage(point1.getXCoordinate(), point2.getXCoordinate()),
                getAverage(point1.getYCoordinate(), point2.getYCoordinate())
        );
    }

    private static List<GeoPoint> getPointsStream() {
        return null;
    }
}