package algonquin.cst2335.myfinalproject.BearGenerator.data;


import java.util.List;

public interface BearGeneratorDAO {

    public long insertImage(Image image);
    public List<Image> getAllText();
    public void deleteImage(Image image);
}
