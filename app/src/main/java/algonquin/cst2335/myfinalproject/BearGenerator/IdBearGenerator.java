package algonquin.cst2335.myfinalproject.BearGenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.BearGeneratorActivityMainBinding;

public class IdBearGenerator extends AppCompatActivity {

    BearGeneratorActivityMainBinding binding;
    EditText widthText;
    EditText heightText;
    Button generateButton;

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