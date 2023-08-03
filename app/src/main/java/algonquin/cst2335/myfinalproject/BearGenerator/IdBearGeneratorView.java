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

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import algonquin.cst2335.myfinalproject.R;

public class IdBearGeneratorView extends AppCompatActivity {

    Button saveButton;
    ImageView imageView;

    ConstraintLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            nextPage2.putExtra("url", url);
            startActivity(nextPage2);
        });

//        saveButton = findViewById(R.id.Savebutton);
//
//        saveButton.setOnClickListener(click -> {
//            AlertDialog.Builder builder = new AlertDialog.Builder(IdBearGeneratorView.this);
//            builder.setMessage("Do you want to save this image?");
//            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Snackbar.make(saveButton,"Do you want to undo?", Snackbar.LENGTH_LONG)
//                            .setAction("Undo", (snackbarClick) -> {
//
//                            })
//                            .show();
//                }
//            });
//            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            AlertDialog alert = builder.create();
//            alert.setTitle("Alert!");
//            alert.show();
//        });
    }
}