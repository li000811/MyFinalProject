package algonquin.cst2335.myfinalproject.BearGenerator.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import algonquin.cst2335.myfinalproject.databinding.BearGeneratorActivityDetailsBinding;

public class BearImageDetailsFragment extends Fragment {
    Image selected;
    public BearImageDetailsFragment(Image m){
        selected = m;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        BearGeneratorActivityDetailsBinding detailsBinding = BearGeneratorActivityDetailsBinding.inflate(inflater);
        detailsBinding.idText.setText(String.valueOf(selected.id));
        detailsBinding.widthText.setText(selected.getWidth());
        detailsBinding.heightText.setText(selected.getHeight());

        return detailsBinding.getRoot();
    }
}
