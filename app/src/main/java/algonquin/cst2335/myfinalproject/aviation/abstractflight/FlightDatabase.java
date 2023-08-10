package algonquin.cst2335.myfinalproject.aviation.abstractflight;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

/**
 * The `FlightDatabase` class is an abstract class representing the Room database for managing flight-related data.
 * It is annotated with `@Database` to define the entities and database version.
 */
@Database(entities = {FlightEntity.class}, version = 1, exportSchema = false)
public abstract class FlightDatabase extends RoomDatabase {

    /**
     * Returns an instance of the Data Access Object (DAO) for performing database operations related to flights.
     *
     * @return An instance of the FlightDAO interface.
     */
    public abstract FlightDAO flightDAO();

}
