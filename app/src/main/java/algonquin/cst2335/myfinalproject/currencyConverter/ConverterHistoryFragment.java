/**
 * CST2335_010_012_Final Project_Currency Converter
 * Changhong Li (041071022)
 */

package algonquin.cst2335.myfinalproject.currencyConverter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.FragmentCurrencyConverterHistoryBinding;
import algonquin.cst2335.myfinalproject.databinding.ItemCurrencyConverterHistoryBinding;

/**
 * The ConverterHistoryFragment class inherits Fragment class, and it is a
 * fragment that displays the currency conversion history using a RecyclerView.
 *
 * @Author Changhong Li
 */
public class ConverterHistoryFragment extends Fragment {
    FragmentCurrencyConverterHistoryBinding binding;

    //Adapter to display the currency conversion history in a RecyclerView.
    private RecyclerView.Adapter myAdapter;

    //Database related objects, which are added in milestone3.
    CurrencyDatabase db;
    CurrencyDAO dao;

    //List to hold the currency conversion history data
    List<Currency> list = new ArrayList<Currency>();

    /**
     * Constructor for the ConverterHistoryFragment.
     *
     * @param context The context of the calling activity or fragment.
     *                It is used to initialize the Room database and
     *                retrieve the currency conversion history.
     */
    public ConverterHistoryFragment(Context context) {
        //database initialization
        db = Room.databaseBuilder(context, CurrencyDatabase.class, "database-name").build();
        dao = db.cDAO();

        //fetch currency conversion history from the database using a separate thread.
        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(()->{
            list = dao.getAllCurrencys();
        });
    }

    /**
     * Called to create the view for this fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate
     * any views in the fragment,
     * @param container If non-null, this is the parent view that the fragment's
     * UI should be attached to.  The fragment should not add the view itself,
     * but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     *
     * @return The view for the fragment's UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCurrencyConverterHistoryBinding.inflate(inflater);

        //set up the RecyclerView to display the currency conversion history
        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                //Inflate the layout for each item in the RecyclerView
                ItemCurrencyConverterHistoryBinding sendBinding =
                        ItemCurrencyConverterHistoryBinding.inflate(getLayoutInflater(),
                                parent, false);
                return new MyRowHolder(sendBinding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
                //bind the data to each item in the RecyclerView.
                holder.currencyFrom.setText(list.get(position).getCurrencyFrom());
                holder.currencyTo.setText(list.get(position).getCurrencyTo());
                holder.amountFrom.setText(list.get(position).getAmountFrom()+"");
                holder.amountTo.setText(list.get(position).getAmountTo()+"");
            }

            @Override
            public int getItemCount() {
                //return the number of items in the currency conversion history list.
                return list.size();
            }
        });

        //set the layout manager for the RecyclerView
        binding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }

    /**
     * Inner class MyRowHolder is a ViewHolder class for each item in the RecyclerView.
     */
    class MyRowHolder extends RecyclerView.ViewHolder{
        TextView currencyFrom;
        TextView currencyTo;
        TextView amountFrom;
        TextView amountTo;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            currencyFrom = itemView.findViewById(R.id.currencyFrom);
            currencyTo = itemView.findViewById(R.id.currencyTo);
            amountFrom = itemView.findViewById(R.id.amountFrom);
            amountTo = itemView.findViewById(R.id.amountTo);

            //set click listener for each item in the RecyclerView.
            itemView.setOnClickListener(click -> {
                int position = getAbsoluteAdapterPosition();

                //show a confirmation dialog to delete the selected history item.
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want to delete this history?").setTitle("Question: ")
                        .setPositiveButton("YES", (dialog, cl) -> {

                            //delete the history item from the database using a separate thread.
                            Executor thread = Executors.newSingleThreadExecutor();
                            thread.execute(() -> {
                                dao.deleteCurrency(list.get(position));
                                //show a Snackbar to indicate the successful deletion.
                                Snackbar.make(currencyFrom, "delete success!",
                                        Snackbar.LENGTH_LONG).show();
                                //remove the item from the list and update the RecyclerView on the main thread.
                                list.remove(position);
                                getActivity().runOnUiThread(()->myAdapter.notifyItemRemoved(position));
                            });
                        }).setNegativeButton("NO", (dialog, cl) -> {
                            //user clicked NO, do nothing
                        }).create().show();
            });
        }
    }


}
