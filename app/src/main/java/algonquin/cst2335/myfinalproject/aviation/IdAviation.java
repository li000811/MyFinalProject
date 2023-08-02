package algonquin.cst2335.myfinalproject.aviation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.adapters.ViewPager2Adapter;

/**
 *  LANDING PAGE FOR THE AVIATION STACK FLIGHT TRACKER.
 *
 *  This file will handle the main activity of the application, including user
 *  interactions and API requests.
 */
public class IdAviation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aviation_activity_main);

        initView();
    }

    private void initView() {
        // load XML widgets from aviation_activity_main.xml
        MaterialToolbar toolbar = findViewById(R.id.toolbar); //previously tool
        ViewPager2 viewPager2 = findViewById(R.id.viewPager); //vp
        TabLayout tabLayout = findViewById(R.id.tabLayout); //lt


        ViewPager2Adapter viewPagerAdapter = new ViewPager2Adapter(this);
        String[] tabs = new String[]{getString(R.string.search), getString(R.string.save)};

        setSupportActionBar(toolbar);


        viewPager2.setAdapter(pager2Adapter);
        for (int i = 0; i < 2; i++) {
            tabLayout.addTab(tabLayout.newTab());
        }

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabs[position]);
            }
        });
        mediator.attach();
    }

//
//    // load variables
//    AviationActivityMainBinding binding;
//    ArrayList<String> flights = new ArrayList<>();

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.aviation_activity_main);
//
//        // Load recycler view
//        binding = AviationActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        binding.recyclerView.setAdapter(
//                new RecyclerView.Adapter<RowHolder>() {// Tells the view how to draw items in the list
//                    /** This function creates a ViewHolder object which we'll learn next.
//                    It represents a single row in the list */
//                    @NonNull
//                    @Override
//                    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                        AviationActivityMainBinding binding = AviationActivityMainBinding.inflate(getLayoutInflater());
//                        return new RowHolder(binding.getRoot());
//                    }
//
//                    /** This function creates a ViewHolder object which we'll learn next.
//                     * It represents a single row in the list */
//                    @Override
//                    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
//                        String obj = flights.get(position);
//                        holder.time.setText(obj);
//                        holder.departure.setText("p2");
//                        holder.arrival.setText("p3");
//                        holder.origin.setText("p4");
//                        holder.destination.setText("p5");
//                        holder.airport.setText("p6");
//                    }
//
//                    /** This function just returns an int specifying how many items to draw. */
//                    @Override
//                    public int getItemCount() {
//                        return 0;
//                    }
//                }
//        );

    //  Displaying Toast
        //Toast.makeText(getApplicationContext(),"PLACEHOLDER TOAST",Toast.LENGTH_SHORT).show();
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