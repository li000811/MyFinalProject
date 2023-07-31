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

/**
 *  LANDING PAGE FOR THE AVIATION STACK FLIGHT TRACKER.
 *
 *  This file will handle the main activity of the application, including user
 *  interactions and API requests.
 */
public class IdAviation extends AppCompatActivity {

    // INNER CLASS SECTION
//        class RowHolder extends RecyclerView.ViewHolder {
//            TextView message;
//            TextView time;
//
//            public RowHolder(@NonNull View itemView){
//                super(itemView);
//                message = itemView.findViewById(R.id.message);
//                time = itemView.findViewById(R.id.time);
//            }
//        }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aviation_activity_main);

    //  Displaying Toast
        Toast.makeText(getApplicationContext(),"Hello Javatpoint",Toast.LENGTH_SHORT).show();
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






























