package algonquin.cst2335.myfinalproject.aviation.DTO;

public class DataDTO {
    private String flight_date;
    private String flight_status;
    private DepartureDTO departure;
    private ArrivalDTO arrival;
    private AirlineDTO airline;
    private FlightDTO flight;

    public String getFlight_date() {
        return flight_date;
    }
    public void setFlight_date(String flight_date) {
        this.flight_date = flight_date;
    }
    public String getFlight_status() {
        return flight_status;
    }
    public void setFlight_status(String flight_status) {
        this.flight_status = flight_status;
    }
    public DepartureDTO getDeparture() {
        return departure;
    }
    public void setDeparture(DepartureDTO departure) {
        this.departure = departure;
    }
    public ArrivalDTO getArrival() {
        return arrival;
    }
    public void setArrival(ArrivalDTO arrival) {
        this.arrival = arrival;
    }
    public AirlineDTO getAirline() {
        return airline;
    }
    public void setAirline(AirlineDTO airline) {
        this.airline = airline;
    }
    public FlightDTO getFlight() {
        return flight;
    }
    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }
}