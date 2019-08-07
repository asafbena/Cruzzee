package cruzzee.schemas;

public class GeoArea {
    private String seaArea;
    private String countryName;
    private double distanceFromCountry;

    public GeoArea(String seaArea, String countryName, double distanceFromCountry) {
        this.seaArea = seaArea;
        this.countryName = countryName;
        this.distanceFromCountry = distanceFromCountry;
    }

    public String getSeaArea() {
        return seaArea;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getDistanceFromCountry() { return distanceFromCountry; }

}
