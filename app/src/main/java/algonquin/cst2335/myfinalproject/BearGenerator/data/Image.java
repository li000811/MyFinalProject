package algonquin.cst2335.myfinalproject.BearGenerator.data;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * author : Chamini Savindya Demuni
 * This class hold the attributes of the image and getter, setters of them.
 */
@Entity
public class Image {

    /**
     * Auto generating id
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    /**
     * url of the image
     */
    @ColumnInfo(name = "image")
    protected String image;
    /**
     * width of the image
     */
    @ColumnInfo(name = "width")
    protected String width;
    /**
     * height of the image
     */
    @ColumnInfo(name = "height")
    protected String height;
    /**
     * inserted mode of the image
     */
    @ColumnInfo(name = "InsertedOrNot")
    protected boolean insertedImg;

    /**
     * no-arguments constructor of Image class
     */
    public Image(){}

    /**
     * parameterized constructor of Image class
     * @param u image url
     * @param w image width
     * @param h image height
     * @param i image insert mode
     */
    public Image(String u, String w, String h, boolean i){
        image = u;
        width = w;
        height = h;
        insertedImg = i;
    }

    /**
     * getter method for image url
     * @return image url as String
     */
    public String getImage() {
        return image;
    }

    /**
     * setter method for image url
     * @param u image url
     */
    public void setUrl(String u) {
        this.image = u;
    }

    /**
     * getter method for image width
     * @return image width as String
     */
    public String getWidth() {
        return width;
    }

    /**
     * setter method for image width
     * @param width image width
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * getter method for image url
     * @return image url as String
     */
    public String getHeight() {
        return height;
    }

    /**
     * setter method for image width
     * @param height image height
     */
    public void setHeight(String height) {
        this.height = height;
    }

    /**
     * getter method for image url
     * @return image url as String
     */
    public boolean isInsertedImg() {
        return insertedImg;
    }

    /**
     * setter method for image mode
     * @param insertedImg image mode
     */
    public void setInsertedImg(boolean insertedImg) {
        this.insertedImg = insertedImg;
    }
}
