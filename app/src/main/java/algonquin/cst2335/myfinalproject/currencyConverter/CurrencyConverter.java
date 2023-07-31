package algonquin.cst2335.myfinalproject.currencyConverter;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
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

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.ActivityCurrencyConverterBinding;

public class CurrencyConverter extends AppCompatActivity {

    ActivityCurrencyConverterBinding binding;

    ArrayList<Currency> results = new ArrayList<Currency>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCurrencyConverterBinding.inflate(getLayoutInflater());
        //loads an XML file on the page
        setContentView(binding.getRoot());

        //this loads the toolbar, in onCreateOptionsMenu()
        setSupportActionBar(binding.converterToolbar); // adds the toolbar, to call onCreateOptionsMenu()


        SharedPreferences pref = getSharedPreferences("Final", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        binding.editAmount .setText(pref.getString("amount",""));
        String[] from = {"USD", "CAD", "AUD"};
        ArrayAdapter adapterFrom = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, from);
        binding.spFrom.setAdapter(adapterFrom);

        String[] to = {"CAD", "AUD", "USD"};
        ArrayAdapter adapterTo = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, to);
        binding.spTo.setAdapter(adapterTo);

        binding.convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CurrencyConverter.this );
//                builder.setMessage("Do you want to convert?").setTitle("Question: ").setPositiveButton("YES", (dialog, cl) -> {
                //add variable "confirm" to prepare for translation
                builder.setMessage(R.string.confirm).setTitle("Question: ").setPositiveButton("YES", (dialog, cl) -> {

                    RequestFromWeb();
//                    Double tot;
//                    Double amount = Double.parseDouble(binding.editAmount.getText().toString());
//                    editor.putString("amount",amount.toString());
//                    editor.apply();
//                    if(binding.spFrom.getSelectedItem().toString().equals("USD")
//                            && binding.spTo.getSelectedItem().toString().equals("CAD")) {
//                        tot = amount * 1.32;
//                        //Currency item;
//                        //item.set()
//                        //currencyList.add(item);
//                        Toast.makeText(getApplicationContext(), tot.toString(), Toast.LENGTH_LONG).show();
//                        Snackbar.make(binding.editAmount, tot.toString(),Snackbar.LENGTH_LONG).show();
//                    }

                }).setNegativeButton("NO", (dialog, cl) -> {

                }).create().show();

            }
        });

    }

    ConverterHistoryFragment  converterHistoryFragment;
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.help){

        }else if(item.getItemId()==R.id.history){
            if(converterHistoryFragment!=null){
                getSupportFragmentManager().beginTransaction().remove(converterHistoryFragment).addToBackStack("").commit();
                converterHistoryFragment = null;
            }else{
                converterHistoryFragment = new ConverterHistoryFragment(results);
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentHistory, converterHistoryFragment).addToBackStack("").commit();
            }
           }
        return true;
    }
    //adds for toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //fill in the rest:
        getMenuInflater().inflate(R.menu.converter_menu, menu);
        return true;
    }

    private void RequestFromWeb(){
        //RequestQueue initialized
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String amountFrom = binding.editAmount.getText().toString();
        String currencyFrom = binding.spFrom.getSelectedItem().toString();
        String currencyTo = binding.spTo.getSelectedItem().toString();

        String url = "https://currency-converter5.p.rapidapi.com/currency/convert";
        Uri.Builder builder = Uri.parse(url).buildUpon();
        builder.appendQueryParameter("format", "json");
        builder.appendQueryParameter("from", currencyFrom);
        builder.appendQueryParameter("to", currencyTo);
        builder.appendQueryParameter("amount", amountFrom);

        //String Request initialized
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, builder.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG,"response :" + response.toString());
                // display the repsonse to the screen
                // it is only for example, you should parse the string
                //  and set the fields in recyclerView
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
                    Toast.makeText(getApplicationContext(),rate_for_amount, Toast.LENGTH_LONG).show();
                    Snackbar.make(binding.editAmount, rate_for_amount,Snackbar.LENGTH_LONG).show();
                    results.add(currency);
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
                Map<String, String> params = new HashMap<String, String>();
                params.put("X-RapidAPI-Key", "3507213528mshdd511c16dee84fbp106387jsn4982d4dd8c14");
                params.put("X-RapidAPI-Host", "currency-converter5.p.rapidapi.com");

                return params;
            }
        };

        requestQueue.add(request);
    }
}