package algonquin.cst2335.myfinalproject.BearGenerator.data;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class BearModel extends ViewModel {
    public MutableLiveData<ArrayList<Image>> images = new MutableLiveData<>();
    public MutableLiveData<Image> selectdImages = new MutableLiveData<>();
}
