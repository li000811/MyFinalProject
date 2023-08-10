package algonquin.cst2335.myfinalproject.BearGenerator.data;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * author : Chamini Savindya Demuni
 * This interface initialize the methods for insert, get, and delete details.
 */
@Dao
public interface BearGeneratorDAO {
    /**
     * Insert function of the image details into the database
     * @param image Image class object
     * @return id of the image as long
     */
    @Insert
    public long insertImage(Image image);

    /**
     * Fetch the image details of the all images
     * @return list of images details
     */
    @Query("SELECT * FROM Image")
    public List<Image> getAllText();

    /**
     * Delete image details using Image class object
     * @param image Image class object
     */
    @Delete
    public void deleteImage(Image image);
}
