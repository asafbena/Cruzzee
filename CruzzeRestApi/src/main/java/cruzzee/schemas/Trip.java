package cruzzee.schemas;

import java.util.List;

public class Trip {
    public List<GeoLine> getRoute() {
        return route;
    }

    public void setRoute(List<GeoLine> route) {
        this.route = route;
    }

    public TripDescription getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(TripDescription tripDescription) {
        this.tripDescription = tripDescription;
    }

    public Trip(List<GeoLine> route, TripDescription tripDescription) {
        this.route = route;
        this.tripDescription = tripDescription;
    }

    private List<GeoLine> route;
    TripDescription tripDescription;
}
