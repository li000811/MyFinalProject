package algonquin.cst2335.myfinalproject.aviation.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.adapters.FlightListAdapter;

public class SearchFragment extends Fragment {

    private static final String KEY = "8e4f8a6f95d24ccff2a43f8f7a05546c";
    private View root;
    private EditText mEtSearch;
    private ConstraintLayout mClRoot;
    private Button mBtnSearch;
    private RecyclerView mRvFlights;
    private FlightListAdapter mAdapter;
    private SharedPreferences sharedPreferences;
    private Gson mGson = new Gson();

    private void initView() {
        mEtSearch = root.findViewById(R.id.et_search);
        mClRoot = root.findViewById(R.id.cl_root);
        mBtnSearch = root.findViewById(R.id.btn_search);
        mRvFlights = root.findViewById(R.id.rv_flights);

        mAdapter = new FightsAdapter();
        mRvFlights.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvFlights.setAdapter(mAdapter);
    }
}