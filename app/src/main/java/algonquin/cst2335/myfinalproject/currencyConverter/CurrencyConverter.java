/**
 * CST2335_010_012_Final Project_Currency Converter
 * Changhong Li (041071022)
 */

package algonquin.cst2335.myfinalproject.currencyConverter;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.ActivityCurrencyConverterBinding;

/**
 * The CurrencyConverter class inherits AppCompatActivity class which
 * is an activity that allows the user to convert currency using
 * real-time exchange rates.
 * This activity uses an API to fetch exchange rate data
 * and displays the converted result to the user.
 *
 * @Author Changhong Li
 */
public class CurrencyConverter extends AppCompatActivity {

    ActivityCurrencyConverterBinding binding;

    //List to store the currency conversion history results
    ArrayList<Currency> results = new ArrayList<Currency>();

    CurrencyDAO dao;

    /**
     * Called when the activity is created. Initializes the layout,
     * database, and user interface components.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Inflate the layout for this activity.
        binding = ActivityCurrencyConverterBinding.inflate(getLayoutInflater());
        //loads an XML file on the page
        setContentView(binding.getRoot());

        //Set up the toolbar for the activity, and this loads the toolbar, in onCreateOptionsMenu()
        setSupportActionBar(binding.converterToolbar); // adds the toolbar, to call onCreateOptionsMenu()

        //Initialize the Room database and its DAO
        CurrencyDatabase db = Room.databaseBuilder(getApplicationContext(), CurrencyDatabase.class,
                "database-name").build();
        dao = db.cDAO();

        // Load the previously entered amount from SharedPreferences and set it in the EditText
        SharedPreferences pref = getSharedPreferences("Final", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        binding.editAmount .setText(pref.getString("amount",""));

        // Set up the spinner adapters for currency selection
        String[] from = {"USD", "CAD", "AUD", "EUR", "HKD"};
        ArrayAdapter adapterFrom = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, from);
        binding.spFrom.setAdapter(adapterFrom);

        String[] to = {"USD", "CAD", "AUD", "EUR", "HKD"};
        ArrayAdapter adapterTo = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, to);
        binding.spTo.setAdapter(adapterTo);

        // Set a click listener for the convert button
        binding.convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show a confirmation dialog before converting the currency
                AlertDialog.Builder builder = new AlertDialog.Builder(CurrencyConverter.this );
                //builder.setMessage("Do you want to convert?").setTitle("Question: ").setPositiveButton("YES", (dialog, cl) -> {
                //add variable "confirm" to prepare for translation
                builder.setMessage(R.string.confirm).setTitle("Question: ").setPositiveButton("YES", (dialog, cl) -> {
                    // Get the amount and selected currencies for conversion and initiate the request
                    Double amount = Double.parseDouble(binding.editAmount.getText().toString());
                    editor.apply();
                    RequestFromWeb();
                }).setNegativeButton("NO", (dialog, cl) -> {
                    // User clicked NO, do nothing
                }).create().show();
            }
        });
    }

    ConverterHistoryFragment  converterHistoryFragment;

    /**
     * Called when an item in the options menu is selected.
     * Handles the selection of menu items from the toolbar.
     *
     * @param item The selected menu item.
     * @return true if the menu item selection is handled, otherwise false.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==R.id.help){
            // Show a toast with a message when the "help" menu item is selected
            Toast.makeText(this, R.string.message,
                    Toast.LENGTH_LONG).show();
        }else if(item.getItemId()==R.id.history){
            if(converterHistoryFragment!=null){
                // If the converterHistoryFragment is already displayed, remove it from the fragment manager
                getSupportFragmentManager().beginTransaction().remove(converterHistoryFragment).addToBackStack("").commit();
                converterHistoryFragment = null;
            }else{
                // If the converterHistoryFragment is not displayed, create and add it to the fragment manager
                converterHistoryFragment = new ConverterHistoryFragment(this);
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentHistory, converterHistoryFragment).addToBackStack("").commit();
            }
        }
        return true;
    }

    //adds for toolbar
    /**
     * Initialize the contents of the options menu.
     * Inflates the menu layout for the toolbar and displays the menu items.
     *
     * @param menu The options menu in which the items are placed.
     * @return true for the menu to be displayed, false otherwise.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        // Inflate the menu layout for the toolbar
        getMenuInflater().inflate(R.menu.converter_menu, menu);
        return true;
    }

    /**
     * Initiates a currency conversion request to a web API using Volley.
     * The result is displayed to the user, and the conversion history
     * is stored in the database.
     */
    private void RequestFromWeb(){
        //Initialize a Volley RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //Get the selected amount and currencies for conversion
        String amountFrom = binding.editAmount.getText().toString();
        String currencyFrom = binding.spFrom.getSelectedItem().toString();
        String currencyTo = binding.spTo.getSelectedItem().toString();

        // Build the URL for the currency conversion API request
        String url = "https://currency-converter5.p.rapidapi.com/currency/convert";
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("format", "json");
        builder.appendQueryParameter("from", currencyFrom);
        builder.appendQueryParameter("to", currencyTo);
        builder.appendQueryParameter("amount", amountFrom);

        // Create a JSON request using Volley's JsonObjectRequest
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, builder.toString(),
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG,"response :" + response.toString());
                // displays response to the screen
                // and set the fields in recyclerView
                // Parse the response JSON and extract the converted amount
                Currency currency = new Currency();
                currency.setCurrencyFrom(currencyFrom);
                currency.setCurrencyTo(currencyTo);
                currency.setAmountFrom(Double.parseDouble(amountFrom));
                JSONObject rates = null;
                try {
                    rates = response.getJSONObject("rates");
                    JSONObject position0 = rates.getJSONObject(currencyTo);
                    String rate_for_amount = position0.getString("rate_for_amount");
                    currency.setAmountTo(Double.parseDouble(rate_for_amount));

                    // Show a Toast with the conversion result to the user
                    Toast.makeText(getApplicationContext(),
                            "Convert " + currencyFrom + " " + Double.parseDouble(amountFrom)
                            + " to " + currencyTo + " \n" + "result is "
                                    + rate_for_amount, Toast.LENGTH_LONG).show();

                    // Insert the conversion result into the database using a separate thread
                    Executor thread = Executors.newSingleThreadExecutor();

                    thread.execute(()->{
                        dao.insertCurrency(currency); //insert result into database
                    });

                    //Snackbar.make(binding.editAmount, rate_for_amount,Snackbar.LENGTH_LONG).show();
                    //results.add(currency);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG,"Error :" + error.toString());
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                // Set the headers required for the API request
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "3507213528mshdd511c16dee84fbp106387jsn4982d4dd8c14");
                params.put("X-RapidAPI-Host", "currency-converter5.p.rapidapi.com");

                return params;
            }
        };

        // Add the request to the Volley RequestQueue
        requestQueue.add(request);
    }
}