package cruzzee.schemas;

public class GeoLine {
    private GeoPoint start;
    private GeoPoint end;

    public GeoLine(GeoPoint startPoint, GeoPoint endPoint) {
        this.start = startPoint;
        this.end = endPoint;
    }

    public GeoPoint getStart() {
        return start;
    }

    public void setStart(GeoPoint start) {
        this.start = start;
    }

    public GeoPoint getEnd() {
        return end;
    }

    public void setEnd(GeoPoint end) {
        this.end = end;
    }
}
