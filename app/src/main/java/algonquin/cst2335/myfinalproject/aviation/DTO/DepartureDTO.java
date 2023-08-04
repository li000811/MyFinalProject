package algonquin.cst2335.myfinalproject.aviation.DTO;

public class DepartureDTO {
    private String iata;
    private String icao;

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
}