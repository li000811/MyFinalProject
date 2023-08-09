package algonquin.cst2335.myfinalproject.aviation.DTO;

/**
 * The `DataDTO` class represents flight data.
 */
public class DataDTO {
    private String flight_date;
    private String flight_status;
    private DepartureDTO departure;
    private ArrivalDTO arrival;
    private FlightDTO flight;

    /**
     * Get the flight date.
     *
     * @return The flight date.
     */
    public String getFlight_date() {
        return flight_date;
    }

    /**
     * Get the flight status.
     *
     * @return The flight status.
     */
    public String getFlight_status() {
        return flight_status;
    }

    /**
     * Set the flight status.
     *
     * @param flight_status The flight status to set.
     */
    public void setFlight_status(String flight_status) {
        this.flight_status = flight_status;
    }

    /**
     * Get the departure information.
     *
     * @return The departure information.
     */
    public DepartureDTO getDeparture() {
        return departure;
    }

    /**
     * Get the arrival information.
     *
     * @return The arrival information.
     */
    public ArrivalDTO getArrival() {
        return arrival;
    }

    /**
     * Get the flight information.
     *
     * @return The flight information.
     */
    public FlightDTO getFlight() {
        return flight;
    }

    /**
     * Set the flight information.
     *
     * @param flight The flight information to set.
     */
    public void setFlight(FlightDTO flight) {
        this.flight = flight;
    }
}
