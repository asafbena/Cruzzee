package cruzzee.coordinateapi;


import cruzzee.schemas.GeoLine;
import cruzzee.schemas.GeoPoint;

import java.util.List;

class AreaDeduction {

    public static double getDoubleMiddle(double value1, double value2) {
        return (value1 + value2) / 2;
    }

    public static GeoPoint getMiddlePoint(GeoLine line) {
        return getMiddlePoint(line.getStart(), line.getEnd());
    }

    public static GeoPoint getMiddlePoint(GeoPoint point1, GeoPoint point2) {
        return new GeoPoint(
                getDoubleMiddle(point1.getXCoordinate(), point2.getXCoordinate()),
                getDoubleMiddle(point1.getYCoordinate(), point2.getYCoordinate())
        );
    }


    private static List<GeoPoint> getPointsStream() {
        return null;
    }
}