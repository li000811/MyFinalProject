package algonquin.cst2335.myfinalproject.BearGenerator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.myfinalproject.BearGenerator.data.BearGeneratorDAO;
import algonquin.cst2335.myfinalproject.BearGenerator.data.BearImageDetailsFragment;
import algonquin.cst2335.myfinalproject.BearGenerator.data.BearModel;
import algonquin.cst2335.myfinalproject.BearGenerator.data.Image;
import algonquin.cst2335.myfinalproject.BearGenerator.data.ImageDatabase;
import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.BearGeneratorActivityLayoutBinding;
import algonquin.cst2335.myfinalproject.databinding.BearGeneratorActivityListBinding;

/**
 * author : Chamini Savindya Demuni
 * This class implement the list of images and handle options such as delete, about.
 */
public class IdBearGeneratorList extends AppCompatActivity {
    /**
     * view binding of IdBearGeneratorList class
     */
    BearGeneratorActivityListBinding binding;
    /**
     * Bitmap obejct to store image
     */
    Bitmap bitmap;
    /**
     * Arraylist of Image class object
     */
    ArrayList<Image> images;
    /**
     * Image class object for selected image
     */
    Image selected;
    /**
     * Image class object for display image
     */
    Image image;
    /**
     * BearGeneratorDAO object for connect database
     */
    BearGeneratorDAO imageDAO;
    /**
     * RecycleView adapter for view image list
     */
    private RecyclerView.Adapter myAdapter;
    /**
     * BearModel class object
     */
    BearModel bearModel;

    /**
     * Get the selected image position
     */
    int position;

    /**
     * Hold the option menu
     * @param menu The options menu in which you place your items.
     *
     * @return menu activity layout
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.image_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Handle the activity for each menu option.
     * @param item The menu item that was selected.
     *
     * @return confirm the menu options existing
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() ==  R.id.about){
            Toast.makeText(this, "Version 1.0,created by Chamini Demuni", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.help){
            AlertDialog.Builder builder = new AlertDialog.Builder((IdBearGeneratorList.this));
            builder.setMessage("1. Enter the width and hiegth of the image that you want." +
                            "2. Then click the generate image button. After that the application shows the preview of the image." +
                            "3. If the image has been reached desired level, click the Confirm button." +
                            "4. Then navigate to the next page that have image list." +
                            "5. If the image has been reached desired level, click the Add Image button." +
                            "6. You can delete images by clicking Delete button." +
                            "7. Question mark symbol shows the instruction of the application use." +
                            "8. About menu item shows the details of the selected image."
                    )
                    .setTitle("Instruction")
                    .setPositiveButton("Ok", ((dialog, cl) -> {
                        dialog.cancel();
                        }))
                    .create().show();
        }
        return true;
    }

    /**
     * Handle the all the display features and option of the interface
     * main method of IdBearGeneratorList class
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = BearGeneratorActivityListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.myToolBar);

        ImageDatabase db = Room.databaseBuilder(getApplicationContext(),
                ImageDatabase.class, "database-name").build();
        imageDAO = db.bearDAO();

        bearModel = new ViewModelProvider(this).get(BearModel.class);
        images = bearModel.images.getValue();

        if(images == null){
            bearModel.images.setValue(images = new ArrayList<>());
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(()->{
                images.addAll(imageDAO.getAllText());

                runOnUiThread(()-> binding.recycleView.setAdapter(myAdapter));
            });
        }

        Intent previousPage = getIntent();
        String url = previousPage.getStringExtra("url");
        String width = previousPage.getStringExtra("width");
        String height = previousPage.getStringExtra("height");
        Toast.makeText(this, width,  Toast.LENGTH_SHORT).show();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
         StrictMode.setThreadPolicy(policy);

        binding.showBtn.setOnClickListener(click ->{
            boolean addBtn = true;
            //add url to the array list
            Image newImage = new Image(url, width, height, addBtn);
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(()->{
                newImage.id = (int) imageDAO.insertImage(newImage);
            });
            images.add(newImage);
            myAdapter.notifyItemInserted(images.size()-1);
        });

        binding.moreBtn.setOnClickListener(click ->{
            Intent firstPage = new Intent(this, IdBearGenerator.class);
            startActivity(firstPage);
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
                image = images.get(position);
                try {
                    InputStream inputImage = (InputStream) new URL(image.getImage()).getContent();
                    bitmap = BitmapFactory.decodeStream(inputImage);
                    inputImage.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
//                holder.widthText.setText(image.getWidth());
                holder.image.setImageBitmap(bitmap);
            }

            @Override
            public int getItemCount() {
                return images.size();
            }

            @Override
            public int getItemViewType(int position){
                return 0;
            }
        });

        bearModel.selectedImages.observe(this, (newImage)-> {
            FragmentManager fMgr = getSupportFragmentManager();
            FragmentTransaction tx = fMgr.beginTransaction();

            BearImageDetailsFragment imageFragment = new BearImageDetailsFragment(newImage);
            tx.add(R.id.fragmentLocation, imageFragment);
            tx.replace(R.id.fragmentLocation, imageFragment);
            tx.commit();
            tx.addToBackStack("");
        });


    }

    /**
     * Handle the RecycleView adapater using ViewHolder parent class
     */
    class MyRowHolder extends RecyclerView.ViewHolder{
        /**
         * Textview object for image width
         */
        TextView widthText;
        /**
         * ImageView object for display image
         */
        ImageView image;
        /**
         * Button object for delete the image
         */
        Button deleteButton;

        public MyRowHolder(@NonNull View itemView){
            super(itemView);
            /**
             * Retrieve the widges from activity layout
             */
            widthText = itemView.findViewById(R.id.widthText);
            image = itemView.findViewById(R.id.imageList);
            deleteButton = itemView.findViewById(R.id.deleteBtn);

            /**
             * Get the position of the selected image by click listener
             */
            itemView.setOnClickListener(click ->{
                position = getAbsoluteAdapterPosition();
                selected = images.get(position);
                bearModel.selectedImages.postValue(selected);
            });

            /**
             * Delete button operation using click listener
             */
            deleteButton.setOnClickListener(click -> {
                position = getAbsoluteAdapterPosition();
                Image m = images.get(position);
                selected = m;
                /**
                 * Alert dialog box for asking delete requirement
                 */
                AlertDialog.Builder builder = new AlertDialog.Builder((IdBearGeneratorList.this));
                builder.setMessage("Do you want to delete the message: ")
                        .setTitle("Question")
                        .setPositiveButton("Yes", ((dialog, cl) -> {
                            Executor thread = Executors.newSingleThreadExecutor();
                            thread.execute(()->{
                                imageDAO.deleteImage(m);
                                images.remove(position);
                                runOnUiThread(()-> myAdapter.notifyItemRemoved(position));
                            });
                            /**
                             * Ask for undoing the deletion
                             */
                            Snackbar.make(deleteButton,"Do you want to undo?", Snackbar.LENGTH_LONG)
                                    .setAction("Undo", (snackbarClick) -> {
                                        images.add(position, selected);
                                        myAdapter.notifyItemInserted(position);
                                    })
                                    .show();
                        }))
                        .setNegativeButton("No", ((dialog, cl) -> {
                            dialog.cancel();
                        }))
                        .create().show();
            });
        }
    }
}