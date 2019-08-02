package cruzzee.schemas;

import java.util.List;

public class Cruzzee {
    private final List<Double> routeGeoPoints;
    private final String startingPoint;
    private final String destination;
    private final long departureDate;
    private final long arrivalDate;
    private final String cargo;

    public Cruzzee(List<Double> routeGeoPoints, String startingPoint, String destination, long departureDate, long arrivalDate, String cargo) {
        this.routeGeoPoints = routeGeoPoints;
        this.startingPoint = startingPoint;
        this.destination = destination;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.cargo = cargo;
    }


    public List<Double> getRouteGeoPoints() {
        return routeGeoPoints;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public String getDestination() {
        return destination;
    }

    public long getDepartureDate() {
        return departureDate;
    }

    public long getArrivalDate() {
        return arrivalDate;
    }

    public String getCargo() {
        return cargo;
    }
}
