package algonquin.cst2335.myfinalproject.aviation.abstractflight;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

@Database(entities = {FlightEntity.class}, version = 1 ,exportSchema = false)
public abstract class FlightDatabase extends RoomDatabase {

    public abstract FlightDAO flightDAO();

}
