package algonquin.cst2335.myfinalproject.aviation.DTO;

/**
 * The `ArrivalDTO` class represents arrival information for a flight.
 */
public class ArrivalDTO {
    private String airport;
    private String iata;
    private String terminal;
    private String gate;
    private Object delay;

    /**
     * Get the airport of arrival.
     *
     * @return The airport of arrival.
     */
    public String getAirport() {
        return airport;
    }

    /**
     * Get the IATA code of arrival.
     *
     * @return The IATA code of arrival.
     */
    public String getIata() {
        return iata;
    }

    /**
     * Set the IATA code of arrival.
     *
     * @param iata The IATA code to set.
     */
    public void setIata(String iata) {
        this.iata = iata;
    }

    /**
     * Get the terminal of arrival.
     *
     * @return The terminal of arrival.
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * Get the gate of arrival.
     *
     * @return The gate of arrival.
     */
    public String getGate() {
        return gate;
    }

    /**
     * Get the delay status of arrival.
     *
     * @return The delay status of arrival as a string.
     */
    public String getDelay() {
        if (delay == null) {
            delay = "";
        }
        return delay.toString();
    }
}
