package cruzzee;

import cruzzee.schemas.GeoPoint;

import java.util.List;

class AreaDeduction {

    private static double getDoubleMiddle(double value1, double value2) {
        return (value1 + value2) / 2;
    }

    private static GeoPoint getMiddlePoint(GeoPoint point1, GeoPoint point2) {
        return new GeoPoint(
                getDoubleMiddle(point1.getXCoordinate(), point2.getXCoordinate()),
                getDoubleMiddle(point1.getYCoordinate(), point2.getYCoordinate())
        );
    }

    private static List<GeoPoint> getPointsStream() {
        return null;
    }

    public static void main(String[] args)
    {

        List<GeoPoint> points = getPointsStream ();
        for (int i = 0; i < points.size(); i += 2) {
            GeoPoint averagePoint = getMiddlePoint (points.get(i), points.get(i+1));

        }
    }
}