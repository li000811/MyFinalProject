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

import java.util.List;

import algonquin.cst2335.myfinalproject.R;

public class SaveFragment extends Fragment {

    private View root;
    private SaveAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (root == null) {
            root = inflater.inflate(R.layout.fragment_save, container, false);
        }
        initView();
        mDb = Room.databaseBuilder(getContext().getApplicationContext(),
                FightDatabase.class, "app.db").build();
        return root;
    }

    private void initView() {
        RecyclerView rv = root.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SaveAdapter();
        rv.setAdapter(mAdapter);

        mAdapter.setListener(new SaveAdapter.onItemClickListener() {
            @Override
            public void callBack(SaveFightBean dataDTO) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FightDao fightDao = mDb.fightDao();
                        fightDao.delete(dataDTO);
                        setData();
                    }
                }).start();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        setData();
    }
    private FightDatabase mDb;

    private void setData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FightDao fightDao = mDb.fightDao();
                List<SaveFightBean> data = fightDao.getAll();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setNewData(data);
                    }
                });
            }
        }).start();
    }
}