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

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.adapters.FlightListAdapter;
import algonquin.cst2335.myfinalproject.aviation.abstractflight.FlightDAO;

public class SaveFragment extends Fragment {

    private View root;
    private FlightListAdapter mAdapter;
    private FlightDatabase mDb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.aviation_fragment_saved, container, false);
        }
        initView();
        mDb = Room.databaseBuilder(getContext().getApplicationContext(),
                FightDatabase.class, "app.db").build();
        return root;
    }

    private void initView() {
        RecyclerView rv = root.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new FlightListAdapter();
        rv.setAdapter(mAdapter);

        mAdapter.setListener(dataDTO -> new Thread(() -> {
            FlightDAO flightDAO = mDb.fightDao();
            fightDao.delete(dataDTO);
            setData();
        }).start());
    }

}