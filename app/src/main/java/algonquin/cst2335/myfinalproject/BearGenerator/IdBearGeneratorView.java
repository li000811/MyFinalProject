package algonquin.cst2335.myfinalproject.BearGenerator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2335.myfinalproject.R;

/**
 * author : Chamini Savindya Demuni
 * This class implement generating of image,
 * preview of the image according to the user inputs.
 */
public class IdBearGeneratorView extends AppCompatActivity {

    /**
     * Button object for image preview confirmation
     */
    Button saveButton;
    /**
     * ImageView object for display image
     */
    ImageView imageView;

    ConstraintLayout coordinator;
    /**
     * RequestQueue object for http request
     */
    RequestQueue queue = null;

    /**
     * main method of IdBearGeneratorView class
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);
        setContentView(R.layout.bear_generator_activity_view);
        Intent previousPage = getIntent();
        String baseUrl = "https://placebear.com/";
        String width  = previousPage.getStringExtra("width");
        String height = previousPage.getStringExtra("height");

        imageView = findViewById(R.id.imageView);
        coordinator = findViewById(R.id.coordinate);

        String url = baseUrl + width + "/" + height;

        Glide.with(this).load(url).into(imageView);

        saveButton = findViewById(R.id.Savebutton);

        saveButton.setOnClickListener(click -> {
            Intent nextPage2 = new Intent(this, IdBearGeneratorList.class);
            nextPage2.putExtra("width", width);
            nextPage2.putExtra("height", height);
            nextPage2.putExtra("url", url);
            startActivity(nextPage2);
        });
    }
}