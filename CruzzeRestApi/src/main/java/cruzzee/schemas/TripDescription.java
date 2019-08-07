package cruzzee.schemas;

import org.springframework.beans.factory.annotation.Value;


public class TripDescription {
    private String departureDate;
    private String vesselContents;

    public TripDescription()
    {

    }
    public TripDescription(String departureDate, String vesselContents) {
        this.departureDate = departureDate;
        this.vesselContents = vesselContents;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getVesselContents() {
        return vesselContents;
    }

    public void setVesselContents(String vesselContents) {
        this.vesselContents = vesselContents;
    }
}
