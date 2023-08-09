package algonquin.cst2335.myfinalproject.aviation;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Room;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.DTO.DataDTO;
import algonquin.cst2335.myfinalproject.aviation.abstractflight.FlightDAO;
import algonquin.cst2335.myfinalproject.aviation.abstractflight.FlightDatabase;
import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

/**
 * The `IdAviation` class represents an activity that displays detailed information about a flight.
 * It allows the user to view flight details and save them to a local database.
 *
 * This class extends `AppCompatActivity`, providing the necessary functionality for creating an Android activity.
 */
public class IdAviation extends AppCompatActivity {

    // UI elements
    private ConstraintLayout mClRoot;
    private MaterialToolbar mTool;
    private TextView mTvDetail;
    private Button mBtnSave;

    /**
     * Called when the activity is first created. Responsible for initializing the UI and data.
     *
     * @param savedInstanceState The saved instance state, if available.
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aviation_activity_detail);

        initView();
        init();
    }

    // Initializes UI elements by finding their views in the layout XML.
    private void initView() {
        mClRoot = findViewById(R.id.cl_root);
        mTool = findViewById(R.id.tool);
        mTvDetail = findViewById(R.id.tv_detail);
        mBtnSave = findViewById(R.id.btn_save);
    }

    // Initializes the activity's functionality.
    private void init() {
        setSupportActionBar(mTool);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Retrieve the flight data from the intent's extra.
        String dataJson = getIntent().getStringExtra("data");

        // Display a toast and return if no data is available.
        if (dataJson == null) {
            return;
        } else {
            Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show();
        }

        // Parse the JSON data into a DataDTO object.
        DataDTO data = new Gson().fromJson(dataJson, DataDTO.class);

        // Create a string representation of flight details.
        String str = getString(R.string.iata) + ":" + data.getFlight().getIata() +
                "\n" + getString(R.string.status) + ":" + data.getFlight_status();

        // Include arrival details if available.
        if (data.getArrival() != null) {
            str += "\n" + getString(R.string.destination) + ":" + data.getArrival().getAirport() +
                    "\n" + getString(R.string.terminal) + ":" + data.getArrival().getTerminal() +
                    "\n" + getString(R.string.gate) + ":" + data.getArrival().getGate() +
                    "\n" + getString(R.string.delay) + ":" + data.getArrival().getDelay();
        }

        // Display the formatted details in the TextView.
        mTvDetail.setText(str);

        // Set up a click listener for the "Save" button.
        mBtnSave.setOnClickListener(view -> {
            // Create and show a loading dialog.
            AlertDialog loadingDialog = new AlertDialog.Builder(IdAviation.this)
                    .setTitle(getString(R.string.tips))
                    .setMessage(getString(R.string.save_data))
                    .setPositiveButton(getString(R.string.confirm), (dialogInterface, i) -> new Thread(() -> {
                        // Initialize the database and DAO.
                        FlightDatabase db = Room.databaseBuilder(getApplicationContext(),
                                FlightDatabase.class, "app.db").build();
                        FlightDAO fightDao = db.flightDAO();

                        // Create a FlightEntity and populate it with data.
                        FlightEntity entity = new FlightEntity();
                        entity.Delay = data.getArrival().getDelay();
                        entity.Gate = data.getArrival().getGate();
                        entity.Terminal = data.getArrival().getTerminal();
                        entity.Destination = data.getArrival().getAirport();
                        entity.status = data.getFlight_status();
                        entity.iata = data.getFlight().getIata();

                        // Insert the entity into the database and provide feedback.
                        fightDao.insert(entity);
                        runOnUiThread(() -> Snackbar.make(mClRoot, getString(R.string.success), Snackbar.LENGTH_SHORT).show());
                    }).start())
                    .setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> {})
                    .create();

            loadingDialog.show();
        });
    }

    /**
     * Handles options menu item selections.
     *
     * @param item The selected menu item.
     * @return `true` if the item is handled, otherwise `false`.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
