package algonquin.cst2335.myfinalproject.BearGenerator.data;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class BearModel extends ViewModel {
    public MutableLiveData<ArrayList<String>> texts = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Bitmap>> images = new MutableLiveData<>();
}
