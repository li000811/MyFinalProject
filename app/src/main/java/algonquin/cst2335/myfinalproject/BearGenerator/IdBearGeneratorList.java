package algonquin.cst2335.myfinalproject.BearGenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import algonquin.cst2335.myfinalproject.BearGenerator.data.BearModel;
import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.BearGeneratorActivityLayoutBinding;
import algonquin.cst2335.myfinalproject.databinding.BearGeneratorActivityListBinding;

public class IdBearGeneratorList extends AppCompatActivity {
    BearGeneratorActivityListBinding binding;
    ArrayList<String> text;
    ArrayList<Bitmap> bitmap;
    private RecyclerView.Adapter myAdapter;
    BearModel bearModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BearGeneratorActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bearModel = new ViewModelProvider(this).get(BearModel.class);
        text = bearModel.texts.getValue();
        bitmap = bearModel.images.getValue();

        if(text == null){
            bearModel.texts.setValue(text = new ArrayList<>());
        }
        if(bitmap == null){
            bearModel.images.setValue(bitmap = new ArrayList<>());
        }

        Intent previousPage = getIntent();
        String url = previousPage.getStringExtra("url");
        String width = previousPage.getStringExtra("width");
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
         StrictMode.setThreadPolicy(policy);
        try {
            InputStream inputImage = (InputStream) new URL(url).getContent();
            bitmap.add(BitmapFactory.decodeStream(inputImage));
            inputImage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        text.add(width);

        binding.showBtn.setOnClickListener(click ->{
            //add url to the array list

            myAdapter.notifyItemInserted(text.size()-1);
            myAdapter.notifyItemInserted(bitmap.size()-1);
        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                BearGeneratorActivityLayoutBinding binding = BearGeneratorActivityLayoutBinding.inflate(getLayoutInflater());
                return new MyRowHolder(binding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                String obj = text.get(position);
                Bitmap imageObj = bitmap.get(position);
                holder.widthText.setText(obj);
                holder.image.setImageBitmap(imageObj);
            }

            @Override
            public int getItemCount() {
                return bitmap.size();
            }

            @Override
            public int getItemViewType(int position){
                return 0;
            }
        });

    }

    class MyRowHolder extends RecyclerView.ViewHolder{
        TextView widthText;
        ImageView image;
        Button deleteButton;

        public MyRowHolder(@NonNull View itemView){
            super(itemView);
            widthText = itemView.findViewById(R.id.widthText);
            image = itemView.findViewById(R.id.imageList);
            deleteButton = itemView.findViewById(R.id.deleteBtn);
        }
    }
}