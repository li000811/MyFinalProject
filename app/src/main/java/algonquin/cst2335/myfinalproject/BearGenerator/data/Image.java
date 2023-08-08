package algonquin.cst2335.myfinalproject.BearGenerator.data;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Image {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    @ColumnInfo(name = "image")
    protected String image;
    @ColumnInfo(name = "width")
    protected String width;
    @ColumnInfo(name = "height")
    protected String height;
    @ColumnInfo(name = "InsertedOrNot")
    protected boolean insertedImg;

    public Image(){}

    public Image(String u, String w, String h, boolean i){
        image = u;
        width = w;
        height = h;
        insertedImg = i;
    }

    public String getImage() {
        return image;
    }

    public void setUrl(String u) {
        this.image = u;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public boolean isInsertedImg() {
        return insertedImg;
    }

    public void setInsertedImg(boolean insertedImg) {
        this.insertedImg = insertedImg;
    }
}
