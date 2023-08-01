package algonquin.cst2335.myfinalproject.aviation;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.ActivityMainBinding;
import algonquin.cst2335.myfinalproject.databinding.AviationActivityMainBinding;

/**
 *  LANDING PAGE FOR THE AVIATION STACK FLIGHT TRACKER.
 *
 *  This file will handle the main activity of the application, including user
 *  interactions and API requests.
 */
public class IdAviation extends AppCompatActivity {

    // INNER CLASS SECTION
        class RowHolder extends RecyclerView.ViewHolder {
            //TextView message;

            public RowHolder(@NonNull View itemView){
                super(itemView);
                //message = itemView.findViewById(R.id.message);
            }
        }

    // load variables
    AviationActivityMainBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aviation_activity_main);

        // Load recycler view
        binding = AviationActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recyclerView.setAdapter(
                new RecyclerView.Adapter<RowHolder>() {// Tells the view how to draw items in the list
                    /** This function creates a ViewHolder object which we'll learn next.
                    It represents a single row in the list */
                    @NonNull
                    @Override
                    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return null;
                    }

                    /** This function creates a ViewHolder object which we'll learn next.
                     * It represents a single row in the list */
                    @Override
                    public void onBindViewHolder(@NonNull RowHolder holder, int position) {

                    }

                    /** This function just returns an int specifying how many items to draw. */
                    @Override
                    public int getItemCount() {
                        return 0;
                    }
                }
        );

    //  Displaying Toast
        Toast.makeText(getApplicationContext(),"PLACEHOLDER TOAST",Toast.LENGTH_SHORT).show();
        //binding.enterBtn DOES NOT WORK

//  // For rotation survivability
//        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
//        messages = chatModel.messages.getValue();
//        if (messages == null){
//            chatModel.messages.postValue(messages = new ArrayList<ChatMessage>());
//        }

    // EVENTS SECTION
            // "Send" button
//            binding.send.setOnClickListener(click -> {
//                messages.add(new ChatMessage(
//                        binding.textField.getText().toString(),
//                        new SimpleDateFormat("EE, dd-MMM-yyyy hh-mm-ss a").format(new Date()),
//                        true
//                ));
//                adapter.notifyItemInserted(messages.size()-1);
//                binding.list.setLayoutManager(new LinearLayoutManager(this)); // display messages
//                binding.textField.setText(""); // clear text
//            });

    // LIST ADAPTER SECTION
//            binding.list.setAdapter(adapter = new RecyclerView.Adapter<RowHolder>() {
//
//                @NonNull
//                @Override
//                public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                    if (viewType == 0){
//                        SentMessageBinding binding = SentMessageBinding.inflate(getLayoutInflater());
//                        return new RowHolder(binding.getRoot());
//                    } else {
//                        ReceiveMessageBinding binding = ReceiveMessageBinding.inflate(getLayoutInflater());
//                        return new RowHolder(binding.getRoot());
//                    }
//
//                }
//
//                @Override
//                public void onBindViewHolder(@NonNull RowHolder holder, int position) {
//                    ChatMessage obj = messages.get(position);
//                    holder.message.setText(obj.getMessage());
//                    holder.time.setText(obj.getTime());
//                }
//
//                @Override
//                public int getItemCount() {
//                    return messages.size();
//                }
//
//                @Override
//                public int getItemViewType(int position) {
//                    return messages.get(position).isSent() ? 0 : 1;
//                }
//
//            });
    }
}































