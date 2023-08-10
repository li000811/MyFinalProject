package algonquin.cst2335.myfinalproject.aviation.DTO;

/**
 * The `FlightDTO` class represents flight information.
 */
public class FlightDTO {
    private String iata;

    /**
     * Get the IATA code of the flight.
     * If the IATA code is null, return a placeholder "-".
     *
     * @return The IATA code of the flight or a placeholder if null.
     */
    public String getIata() {
        if (iata == null) {
            iata = "-";
        }
        return iata;
    }

    /**
     * Set the IATA code of the flight.
     *
     * @param iata The IATA code to set.
     */
    public void setIata(String iata) {
        this.iata = iata;
    }
}
