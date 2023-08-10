package algonquin.cst2335.myfinalproject.BearGenerator.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import algonquin.cst2335.myfinalproject.databinding.BearGeneratorActivityDetailsBinding;

/**
 * author : Chamini Savindya Demuni
 * Handle the details and set the details to the fragment
 */
public class BearImageDetailsFragment extends Fragment {
    /**
     * Selected image get as a Image class object
     */
    Image selected;

    /**
     * parameterized constructor of BearImageDetailsFragment
     * @param m Image class object
     */
    public BearImageDetailsFragment(Image m){
        selected = m;
    }

    /**
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return widget binding object
     */
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
