/**
 * CST2335_010_012_Final Project
 */
package algonquin.cst2335.myfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import algonquin.cst2335.myfinalproject.BearGenerator.IdBearGenerator;

import algonquin.cst2335.myfinalproject.currencyConverter.CurrencyConverter;
import algonquin.cst2335.myfinalproject.aviation.fragments.MainFlightFragment;

import algonquin.cst2335.myfinalproject.databinding.ActivityMainBinding;

/**
 * MainActivity represents the main entry point of the Android application. It extends
 * {@link androidx.appcompat.app.AppCompatActivity} and serves as the launcher activity.
 * This activity inflates the layout XML file "activity_main.xml" using the
 * {@link ActivityMainBinding} class and sets up click listeners for various buttons.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is created. This method sets up the user interface and
     * initializes click listeners for buttons.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *                           being shut down, this Bundle contains the data it most
     *                           recently supplied. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the layout and bind UI elements
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
      
      // Set up click listener for the Flight Tracker button
        binding.idAviationTracker.setOnClickListener( 
          click -> startActivity(new Intent(this, MainFlightFragment.class)));

        // Set up click listener for the CurrencyConverter button
        binding.idCurrencyConverter.setOnClickListener(
          click -> startActivity(new Intent(this, CurrencyConverter.class)));
    }
}