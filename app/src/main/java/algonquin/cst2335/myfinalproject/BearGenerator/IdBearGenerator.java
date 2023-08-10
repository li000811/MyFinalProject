package algonquin.cst2335.myfinalproject.BearGenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.BearGeneratorActivityMainBinding;

/**
 * author : Chamini Savindya Demuni
 * This class implement the user inputs for generate the image
 */
public class IdBearGenerator extends AppCompatActivity {

    /**
     * view binding of IdBearGenerator class
     */
    BearGeneratorActivityMainBinding binding;
    /**
     * Edit text object for image width
     */
    EditText widthText;
    /**
     * Edit text object for image height
     */
    EditText heightText;
    /**
     * Button object for image gerenator
     */
    Button generateButton;

    /**
     * main method for runn the IdBearGenerator class
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bear_generator_activity_main);

        widthText = findViewById(R.id.widthText);
        heightText = findViewById(R.id.heightText);
        generateButton = findViewById(R.id.GenerateBtn);

        SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        String width = prefs.getString("Width", "");
        String height = prefs.getString("Height", "");
        widthText.setText(width);
        heightText.setText(height);

        generateButton.setOnClickListener(click -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString( "Width", widthText.getText().toString());
            editor.putString("Height", heightText.getText().toString());
            editor.apply();
            Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_SHORT).show();

            //Open the view image page
            Intent nextPage = new Intent(this, IdBearGeneratorView.class);
            nextPage.putExtra("width", widthText.getText().toString());
            nextPage.putExtra("height", heightText.getText().toString());
            startActivity(nextPage);

        });
    }
}