package cruzzee.schemas;

public class GeoArea {
    private String seaArea;
    private String countryName;
    private double distanceFromCountry;

    public GeoArea(String seaArea, String countryName) {
        this.seaArea = seaArea;
        this.countryName = countryName;
        this.distanceFromCountry = 0;
    }

    public String getSeaArea() {
        return seaArea;
    }

    public String getCountryName() {
        return countryName;
    }

    public double getDistanceFromCountry() { return distanceFromCountry; }

}
