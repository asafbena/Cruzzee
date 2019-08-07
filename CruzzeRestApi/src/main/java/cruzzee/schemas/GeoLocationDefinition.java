package cruzzee.schemas;

public class GeoLocationDefinition {
    private String area;
    private String country;
    private double distanceFromCountry;

    public GeoLocationDefinition() {

    }

    public GeoLocationDefinition(String area, String country) {
        this(area, country, 0);
    }

    public GeoLocationDefinition(String area, String country, double distanceFromCountry) {
        this.area = area;
        this.country = country;
        this.distanceFromCountry = distanceFromCountry;
    }

    public String getArea() {
        return area;
    }

    public String getCountry() {
        return country;
    }

    public double getDistanceFromCountry() {
        return distanceFromCountry;
    }

}
