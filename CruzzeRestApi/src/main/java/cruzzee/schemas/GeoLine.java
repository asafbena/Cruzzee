package cruzzee.schemas;

public class GeoLine {
    private double startX;
    private double startY;
    private double endX;
    private double endY;


    public GeoLine(double startX, double startY, double endX, double endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() { return endY; }

    public GeoPoint getStartPoint() { return new GeoPoint(this.startX, this.startY); }

    public GeoPoint getEndPoint() { return new GeoPoint(this.endX, this.endY); }

}
