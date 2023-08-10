package algonquin.cst2335.myfinalproject.aviation.abstractflight;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

/**
 * The `FlightDAO` interface defines the Data Access Object (DAO) methods for performing database operations
 * related to flight entities.
 */
@Dao
public interface FlightDAO {

    /**
     * Retrieve all flight entities from the database.
     *
     * @return A list of all flight entities.
     */
    @Query("SELECT * FROM flight_table")
    List<FlightEntity> getAll();

    /**
     * Insert one or more flight entities into the database.
     *
     * @param flightEntities The flight entities to insert.
     */
    @Insert
    void insert(FlightEntity... flightEntities);

    /**
     * Delete a specific flight entity from the database.
     *
     * @param flightEntity The flight entity to delete.
     */
    @Delete
    void delete(FlightEntity flightEntity);
}
