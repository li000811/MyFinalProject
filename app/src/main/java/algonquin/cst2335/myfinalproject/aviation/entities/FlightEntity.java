package algonquin.cst2335.myfinalproject.aviation.entities;

@Entity(tableName = "flight_table")
public class FlightEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "iata")
    public String iata;

    @ColumnInfo(name = "status")
    public String status;

    @ColumnInfo(name = "Destination")
    public String Destination;

    @ColumnInfo(name = "Terminal")
    public String Terminal;

    @ColumnInfo(name = "Gate")
    public String Gate;

    @ColumnInfo(name = "Delay")
    public String Delay;

}
