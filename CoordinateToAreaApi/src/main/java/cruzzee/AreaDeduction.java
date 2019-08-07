package cruzzee;

import org.geonames.WebService;
import schemas.GeoLine;
import schemas.GeoPoint;

import java.util.List;

class AreaDeduction {

    public static double getDoubleMiddle(double value1, double value2) {
        return (value1 + value2) / 2;
    }

    public static GeoPoint getMiddlePoint(GeoLine line) {
        return getMiddlePoint(line.getStartPoint(), line.getEndPoint());
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

    public static void main(String[] args) throws Exception {
        List<GeoPoint> points = getPointsStream ();
        for (int i = 0; i < points.size(); i += 2) {
            GeoPoint averagePoint = getMiddlePoint (points.get(i), points.get(i+1));
            String area = WebService.ocean(10.411528,107.092511);

        }
    }
}