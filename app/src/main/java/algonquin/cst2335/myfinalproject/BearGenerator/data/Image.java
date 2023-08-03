package algonquin.cst2335.myfinalproject.BearGenerator.data;

public class Image {
    protected String url;
    protected String width;
    protected String height;
    protected boolean insertedImg;

    Image(String u, String w, String h, boolean i){
        url = u;
        width = w;
        height = h;
        insertedImg = i;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
