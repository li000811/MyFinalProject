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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.DTO.DataDTO;
import algonquin.cst2335.myfinalproject.aviation.IdAviation;
import algonquin.cst2335.myfinalproject.aviation.adapters.SearchFlightListAdapter;
import algonquin.cst2335.myfinalproject.aviation.entities.Flight;
import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

/**
 * The `SearchFlightFragment` class represents a fragment that allows users to search for flight information.
 * It displays a search input field, a search button, and a list of search results.
 *
 * This class extends `Fragment`, providing the necessary functionality for creating an Android fragment.
 */
public class SearchFlightFragment extends Fragment {

    private EditText mEtSearch;
    private ConstraintLayout mClRoot;
    private Button mBtnSearch;
    private RecyclerView mRvFlights;
    private SearchFlightListAdapter mAdapter;

    private static final String KEY = "8e4f8a6f95d24ccff2a43f8f7a05546c";

    private SharedPreferences sharedPreferences;

    private Gson mGson = new Gson();

    // Initializes UI elements and sets up the RecyclerView.
    private void initView() {
        mEtSearch = root.findViewById(R.id.et_search);
        mClRoot = root.findViewById(R.id.cl_root);
        mBtnSearch = root.findViewById(R.id.btn_search);
        mRvFlights = root.findViewById(R.id.rv_flights);

        mAdapter = new SearchFlightListAdapter();
        mRvFlights.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvFlights.setAdapter(mAdapter);
    }

    // Initializes the fragment's functionality.
    private void init() {
        sharedPreferences = requireContext().getSharedPreferences("FLIGHTS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mBtnSearch.setOnClickListener(view -> {
            String key = mEtSearch.getText().toString();
            if (TextUtils.isEmpty(key)) {
                Toast.makeText(getContext(), getContext().getString(R.string.search_error_msg), Toast.LENGTH_SHORT).show();
                return;
            }
            editor.putString("KEY", key);
            editor.apply();
            requestData(key);
        });

        mAdapter.setListener(dataDTO -> {
            Intent intent = new Intent(getContext(), IdAviation.class); // Verify if this is the correct class.
            String data = mGson.toJson(dataDTO);
            intent.putExtra("data", data);
            startActivity(intent);
        });

        String key = sharedPreferences.getString("KEY", "");
        mEtSearch.setText(key);
    }

    // Sends a request to fetch flight data based on the provided key.
    private void requestData(String key) {
        AlertDialog loadingDialog = new AlertDialog.Builder(getContext()).setMessage(getContext().getString(R.string.loading)).create();
        loadingDialog.show();
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("http://api.aviationstack.com/v1/flights?access_key=" + KEY + "&dep_iata=" + key,
                response -> {
                    loadingDialog.dismiss();
                    Snackbar.make(mClRoot, getContext().getString(R.string.success), Snackbar.LENGTH_SHORT).show();
                    Gson gson = new Gson();
                    try {
                        Flight flight = gson.fromJson(response, Flight.class);
                        List<DataDTO> d = flight.getData();
                        List<DataDTO> reallyData = new ArrayList<>();
                        for (int i = 0; i < d.size(); i++) {
                            if (d.get(i).getFlight() != null) {
                                reallyData.add(d.get(i));
                            }
                        }
                        mAdapter.setNewData(reallyData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }, error -> {
            loadingDialog.dismiss();
            Snackbar.make(mClRoot, getContext().getString(R.string.failed) + error.toString(), Snackbar.LENGTH_SHORT).show();
        }
        );
        mQueue.add(stringRequest);
    }

    private View root;

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
            root = inflater.inflate(R.layout.aviation_fragment_search, container, false);
        }
        initView();
        init();
        return root;
    }
}
