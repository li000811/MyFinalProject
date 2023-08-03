package algonquin.cst2335.myfinalproject.aviation.abstractflight;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

@Dao
public interface FlightDAO {

    @Query("SELECT * FROM flight_table")
    List<FlightEntity> getAll();

    @Insert
    void insert(FlightEntity... flightEntities);

    @Delete
    void delete(FlightEntity flightEntities);
}