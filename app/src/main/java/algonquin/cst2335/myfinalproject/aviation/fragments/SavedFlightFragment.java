package algonquin.cst2335.myfinalproject.aviation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.abstractflight.FlightDAO;
import algonquin.cst2335.myfinalproject.aviation.abstractflight.FlightDatabase;
import algonquin.cst2335.myfinalproject.aviation.adapters.SavedFlightListAdapter;
import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

/**
 * The `SavedFlightFragment` class represents a fragment that displays a list of saved flight data.
 * It allows users to view and delete saved flight entries.
 *
 * This class extends `Fragment`, providing the necessary functionality for creating an Android fragment.
 */
public class SavedFlightFragment extends Fragment {

    private View root;
    private SavedFlightListAdapter mAdapter;

    /**
     * Called when the fragment's view should be created.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The created View; or null.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.aviation_fragment_saved, container, false);
        }
        initView();
        mDb = Room.databaseBuilder(getContext().getApplicationContext(),
                FlightDatabase.class, "app.db").build();
        return root;
    }

    /**
     * Initializes UI elements and sets up the RecyclerView.
     */
    private void initView() {
        RecyclerView rv = root.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SavedFlightListAdapter();
        rv.setAdapter(mAdapter);

        mAdapter.setListener(dataDTO -> new Thread(() -> {
            FlightDAO fightDao = mDb.flightDAO();
            fightDao.delete(dataDTO);
            setData();
        }).start());
    }

    /**
     * Called when the fragment is resumed. Refreshes the displayed data.
     */
    @Override
    public void onResume() {
        super.onResume();
        setData();
    }

    private FlightDatabase mDb;

    /**
     * Fetches saved flight data from the database and updates the UI.
     */
    private void setData() {
        new Thread(() -> {
            FlightDAO fightDao = mDb.flightDAO();
            List<FlightEntity> data = fightDao.getAll();
            getActivity().runOnUiThread(() -> mAdapter.setNewData(data));
        }).start();
    }
}
