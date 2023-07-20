package algonquin.cst2335.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import algonquin.cst2335.myfinalproject.currencyConverter.CurrencyConverter;
import algonquin.cst2335.myfinalproject.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        binding.idAviationTracker.setOnClickListener( click ->
//                startActivity(new Intent(this, AviationTracker.class))
//        );

        binding.idCurrencyConverter.setOnClickListener( click ->
                startActivity(new Intent(this, CurrencyConverter.class))
        );

//        binding.idTriviaQuestion.setOnClickListener( click ->
//                startActivity(new Intent(this, TriviaQuestion.class))
//        );
//        binding.idBearGenerator.setOnClickListener( click ->
//                startActivity(new Intent(this, BearGenerator.class))
//        );

    }
}