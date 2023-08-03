package algonquin.cst2335.myfinalproject.aviation.DTO;

public class DepartureDTO {
    private String airport;
    private String timezone;
    private String iata;
    private String icao;
    private Object terminal;
    private String gate;
    private Object delay;
    private String scheduled;
    private String estimated;
    private Object actual;
    private Object estimated_runway;
    private Object actual_runway;

    public String getAirport() {
        return airport;
    }
    public void setAirport(String airport) {
        this.airport = airport;
    }
    public String getTimezone() {
        return timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    public String getIata() {return iata;}
    public void setIata(String iata) {
        this.iata = iata;
    }
    public String getIcao() {
        return icao;
    }
    public void setIcao(String icao) {
        this.icao = icao;
    }
    public Object getTerminal() {
        return terminal;
    }
    public void setTerminal(Object terminal) {
        this.terminal = terminal;
    }
    public String getGate() {
        return gate;
    }
    public void setGate(String gate) {
        this.gate = gate;
    }
    public Object getDelay() {
        return delay;
    }
    public void setDelay(Object delay) {
        this.delay = delay;
    }
    public String getScheduled() {
        return scheduled;
    }
    public void setScheduled(String scheduled) {
        this.scheduled = scheduled;
    }
    public String getEstimated() {
        return estimated;
    }
    public void setEstimated(String estimated) {
        this.estimated = estimated;
    }
    public Object getActual() {
        return actual;
    }
    public void setActual(Object actual) {
        this.actual = actual;
    }
    public Object getEstimated_runway() {
        return estimated_runway;
    }

    public void setEstimated_runway(Object estimated_runway) {
        this.estimated_runway = estimated_runway;
    }

    public Object getActual_runway() {
        return actual_runway;
    }

    public void setActual_runway(Object actual_runway) {
        this.actual_runway = actual_runway;
    }
}