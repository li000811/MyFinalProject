package algonquin.cst2335.myfinalproject.aviation;

/**
 * Represents a Flight object containing information about a flight's origin, destination,
 * terminal, gate, and delay (if any).
 */
public class Flight {

    private String origin;
    private String destination;
    private String terminal;
    private String gate;
    private String delay;

    /**
     * Default constructor for the Flight class.
     */
    public Flight() {}

    /**
     * Constructs a Flight object with specified origin, destination, terminal, and gate.
     *
     * @param origin      The airport code representing the flight's origin.
     * @param destination The airport code representing the flight's destination.
     * @param terminal    The terminal at the origin airport for the flight.
     * @param gate        The gate at the origin airport for the flight.
     */
    public Flight(String origin, String destination, String terminal, String gate) {
        this.origin = origin;
        this.destination = destination;
        this.terminal = terminal;
        this.gate = gate;
    }

    /**
     * Get the airport code representing the flight's origin.
     *
     * @return The origin airport code as a String.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Set the airport code representing the flight's origin.
     *
     * @param origin The origin airport code to be set.
     * @return The Flight object to enable method chaining.
     */
    public Flight setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    /**
     * Get the airport code representing the flight's destination.
     *
     * @return The destination airport code as a String.
     */
    public String getDestination() {
        return destination;
    }

    /**
     * Set the airport code representing the flight's destination.
     *
     * @param destination The destination airport code to be set.
     * @return The Flight object to enable method chaining.
     */
    public Flight setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    /**
     * Get the terminal at the origin airport for the flight.
     *
     * @return The terminal at the origin airport as a String.
     */
    public String getTerminal() {
        return terminal;
    }

    /**
     * Set the terminal at the origin airport for the flight.
     *
     * @param terminal The terminal at the origin airport to be set.
     * @return The Flight object to enable method chaining.
     */
    public Flight setTerminal(String terminal) {
        this.terminal = terminal;
        return this;
    }

    /**
     * Get the gate at the origin airport for the flight.
     *
     * @return The gate at the origin airport as a String.
     */
    public String getGate() {
        return gate;
    }

    /**
     * Set the gate at the origin airport for the flight.
     *
     * @param gate The gate at the origin airport to be set.
     * @return The Flight object to enable method chaining.
     */
    public Flight setGate(String gate) {
        this.gate = gate;
        return this;
    }

    /**
     * Get the delay status for the flight (if any).
     *
     * @return The delay status as a String, or null if no delay.
     */
    public String getDelay() {
        return delay;
    }

    /**
     * Set the delay status for the flight.
     *
     * @param delay The delay status to be set.
     * @return The Flight object to enable method chaining.
     */
    public Flight setDelay(String delay) {
        this.delay = delay;
        return this;
    }
}