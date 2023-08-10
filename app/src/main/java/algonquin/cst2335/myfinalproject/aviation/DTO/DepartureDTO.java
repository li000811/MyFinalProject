package algonquin.cst2335.myfinalproject.aviation.DTO;

/**
 * The `DepartureDTO` class represents departure information for a flight.
 */
public class DepartureDTO {
    private String iata;

    /**
     * Get the IATA code of departure.
     *
     * @return The IATA code of departure.
     */
    public String getIata() {
        return iata;
    }

    /**
     * Set the IATA code of departure.
     *
     * @param iata The IATA code to set.
     */
    public void setIata(String iata) {
        this.iata = iata;
    }
}
