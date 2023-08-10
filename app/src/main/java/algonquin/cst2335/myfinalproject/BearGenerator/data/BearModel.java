package algonquin.cst2335.myfinalproject.BearGenerator.data;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

/**
 * author : Chamini Savindya Demuni
 * Bear model class for handle the variables when rotations
 */
public class BearModel extends ViewModel {
    /**
     * mutable variable for Image class object
     */
    public MutableLiveData<ArrayList<Image>> images = new MutableLiveData<>();

    /**
     * mutable variable for Image class object for selected image from list
     */
    public MutableLiveData<Image> selectedImages = new MutableLiveData<>();
}
