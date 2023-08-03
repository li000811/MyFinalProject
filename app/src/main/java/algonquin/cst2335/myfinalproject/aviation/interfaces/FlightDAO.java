package algonquin.cst2335.myfinalproject.aviation.interfaces;

import java.util.List;

@Dao
public interface FlightDAO {

    @Query("SELECT * FROM fight_table")
    List<FlightEntity> getAll();

    @Insert
    void insert(FlightEntity... flightEntities);

    @Delete
    void delete(FlightEntity flightEntities);
}