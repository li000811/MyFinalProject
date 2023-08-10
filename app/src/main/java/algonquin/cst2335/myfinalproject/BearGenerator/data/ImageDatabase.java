package algonquin.cst2335.myfinalproject.BearGenerator.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * author : Chamini Savindya Demuni
 * Abstract class for generate the database
 */
@Database(entities = {Image.class}, version = 1)
public abstract class ImageDatabase extends RoomDatabase {

    /**
     * Abstract method for connection between database
     * @return BearGeneratorDAO class object
     */
    public abstract BearGeneratorDAO bearDAO();
}
