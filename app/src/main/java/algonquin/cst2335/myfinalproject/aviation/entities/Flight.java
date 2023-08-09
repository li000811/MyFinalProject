package algonquin.cst2335.myfinalproject.aviation.entities;

import java.util.List;

import algonquin.cst2335.myfinalproject.aviation.DTO.DataDTO;

/**
 * The `Flight` class represents a collection of flight data.
 * It contains a list of `DataDTO` objects that hold detailed information about individual flights.
 */
public class Flight {
    /**
     * The list of `DataDTO` objects representing individual flight data.
     */
    private List<DataDTO> data;

    /**
     * Get the list of `DataDTO` objects containing flight data.
     *
     * @return The list of flight data.
     */
    public List<DataDTO> getData() {
        return data;
    }
}
