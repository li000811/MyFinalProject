package algonquin.cst2335.myfinalproject.BearGenerator.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BearGeneratorDAO {
    @Insert
    public long insertImage(Image image);
    @Query("SELECT * FROM Image")
    public List<Image> getAllText();
    @Delete
    public void deleteImage(Image image);
}
