package algonquin.cst2335.myfinalproject.BearGenerator.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Image.class}, version = 1)
public abstract class ImageDatabase extends RoomDatabase {

    public abstract BearGeneratorDAO bearDAO();
}
