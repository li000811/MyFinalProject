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
import algonquin.cst2335.myfinalproject.aviation.abstractflight.FlightDatabase;
import algonquin.cst2335.myfinalproject.aviation.adapters.SavedFlightListAdapter;
import algonquin.cst2335.myfinalproject.aviation.adapters.SearchFlightListAdapter;
import algonquin.cst2335.myfinalproject.aviation.abstractflight.FlightDAO;
import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

public class SavedFlightFragment extends Fragment {

    private View root;
    private SavedFlightListAdapter mAdapter;
    private FlightDatabase mDb;

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

    private void initView() {
        RecyclerView rv = root.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SavedFlightListAdapter();
        rv.setAdapter(mAdapter);

        mAdapter.setListener(flightEntity -> new Thread(() -> {
            FlightDAO flightDAO = mDb.flightDAO();
            flightDAO.delete(flightEntity);
            setData();
        }).start());
    }

    private void setData() {
        new Thread(() -> {
            FlightDAO flightDAO = mDb.flightDAO();
            List<FlightEntity> data = flightDAO.getAll();
            getActivity().runOnUiThread(() -> mAdapter.setNewData(data));
        }).start();
    }
}