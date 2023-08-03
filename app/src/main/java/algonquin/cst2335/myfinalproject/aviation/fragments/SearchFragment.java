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
import algonquin.cst2335.myfinalproject.aviation.interfaces.OnItemClickListener;

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
        mClRoot = root.findViewById(R.id.cl_root);
        mEtSearch = root.findViewById(R.id.et_search);
        mBtnSearch = root.findViewById(R.id.btn_search);
        mRvFlights = root.findViewById(R.id.rv_flights);

        mAdapter = new FlightListAdapter();
        mRvFlights.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvFlights.setAdapter(mAdapter);
    }

    private void init() {
        sharedPreferences = getContext().getSharedPreferences("FLIGHTS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        mBtnSearch.setOnClickListener(view -> {
            String IATAcode = mEtSearch.getText().toString();
            if (TextUtils.isEmpty(IATAcode)) {
                Toast.makeText(getContext(), getContext().getString(R.string.search_error_msg), Toast.LENGTH_SHORT).show();
                return;
            }
            editor.putString("KEY", IATAcode);
            editor.apply();

            requestData(IATAcode);
        });

        mAdapter.setListener(new OnItemClickListener() {
            @Override
            public void callBack(FightsBean.DataDTO dataDTO) {

                Intent intent = new Intent(getContext(), FightActivity.class);
                String data = mGson.toJson(dataDTO);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });

        String key = sharedPreferences.getString("KEY", "");
        mEtSearch.setText(key);
    }

    private void requestData(String key) {
        AlertDialog loadingDialog = new AlertDialog.Builder(getContext()).setMessage(getContext().getString(R.string.loading)).create();
        loadingDialog.show();
        RequestQueue mQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest("http://api.aviationstack.com/v1/flights?access_key=" + access_key + "&dep_iata=" + key, response -> {
            loadingDialog.dismiss();
            Snackbar.make(mClRoot, getContext().getString(R.string.success), Snackbar.LENGTH_SHORT).show();
            Gson gson = new Gson();
            try {
                FightsBean data = gson.fromJson(response, FightsBean.class);
                List<FightsBean.DataDTO> d = data.getData();
                List<FightsBean.DataDTO> reallyData = new ArrayList<>();
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
}