package schemas;

public class GeoLocationDefinition {
    private String Area;
    private String Country;


    public GeoLocationDefinition(String Area, String Country) {
        this.Area = Area;
        this.Country = Area;
    }

    public String getArea() {
        return Area;
    }

    public String getCountry() {
        return Country;
    }
}
