package algonquin.cst2335.myfinalproject.aviation.DTO;

public class FlightDTO {
    private String number;
    private String iata;
    private String icao;
    private CodesharedDTO codeshared;

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getIata() {
        if (iata == null) {
            iata = "-";
        }
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }
    public String getIcao() {
        return icao;
    }
    public void setIcao(String icao) {
        this.icao = icao;
    }
    public CodesharedDTO getCodeshared() {
        return codeshared;
    }
    public void setCodeshared(CodesharedDTO codeshared) {
        this.codeshared = codeshared;
    }
}