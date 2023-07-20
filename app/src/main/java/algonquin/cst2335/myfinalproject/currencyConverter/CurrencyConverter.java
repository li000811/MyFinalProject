package algonquin.cst2335.myfinalproject.currencyConverter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.ActivityCurrencyConverterBinding;
import algonquin.cst2335.myfinalproject.databinding.ActivityMainBinding;

public class CurrencyConverter extends AppCompatActivity {

    //   Arraylist<Currency> currencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityCurrencyConverterBinding binding = ActivityCurrencyConverterBinding.inflate(getLayoutInflater());
        //loads an XML file on the page
        setContentView(binding.getRoot());

        //this loads the toolbar, in onCreateOptionsMenu()
        setSupportActionBar(binding.converterToolbar); // adds the toolbar, to call onCreateOptionsMenu()


        SharedPreferences pref = getSharedPreferences("Final", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        binding.editAmount .setText(pref.getString("amount",""));
        String[] from = {"USD", "RMB"};
        ArrayAdapter adapterFrom = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, from);
        binding.spFrom.setAdapter(adapterFrom);

        String[] to = {"CAD", "RMB"};
        ArrayAdapter adapterTo = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, to);
        binding.spTo.setAdapter(adapterTo);

        binding.convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(CurrencyConverter.this );
                builder.setMessage("Do you want to convert?").setTitle("Question: ").setPositiveButton("YES", (dialog, cl) -> {

                    Double tot;
                    Double amount = Double.parseDouble(binding.editAmount.getText().toString());
                    editor.putString("amount",amount.toString());
                    editor.apply();
                    if(binding.spFrom.getSelectedItem().toString().equals("USD")
                            && binding.spTo.getSelectedItem().toString().equals("CAD")) {
                        tot = amount * 1.32;
                        //Currency item;
                        //item.set()
                        //currencyList.add(item);
                        Toast.makeText(getApplicationContext(), tot.toString(), Toast.LENGTH_LONG).show();
                        Snackbar.make(binding.editAmount, tot.toString(),Snackbar.LENGTH_LONG).show();
                    }

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
                converterHistoryFragment = new ConverterHistoryFragment();
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
}