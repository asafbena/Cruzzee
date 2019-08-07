package cruzzee.schemas;

public class TripDescription {
    private int departureDate;
    private String vesselContents;

    public int getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(int departureDate) {
        this.departureDate = departureDate;
    }

    public String getVesselContents() {
        return vesselContents;
    }

    public void setVesselContents(String vesselContents) {
        this.vesselContents = vesselContents;
    }

    public TripDescription(int departureDate, String vesselContents) {
        this.departureDate = departureDate;
        this.vesselContents = vesselContents;
    }
}
