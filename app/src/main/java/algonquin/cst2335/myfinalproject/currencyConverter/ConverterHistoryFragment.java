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

public class ConverterHistoryFragment extends Fragment {
    FragmentCurrencyConverterHistoryBinding binding;

    private RecyclerView.Adapter myAdapter;
    //add database milestone3
    CurrencyDatabase db;
    CurrencyDAO dao;

    List<Currency> list = new ArrayList<Currency>();

    public ConverterHistoryFragment(Context context) {
        //database
        db = Room.databaseBuilder(context, CurrencyDatabase.class, "database-name").build();
        dao = db.cDAO();
        Executor thread = Executors.newSingleThreadExecutor();
        thread.execute(()->{
            list = dao.getAllCurrencys();
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentCurrencyConverterHistoryBinding.inflate(inflater);

        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ItemCurrencyConverterHistoryBinding sendBinding =
                        ItemCurrencyConverterHistoryBinding.inflate(getLayoutInflater(),
                                parent, false);
                return new MyRowHolder(sendBinding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

                holder.currencyFrom.setText(list.get(position).getCurrencyFrom());
                holder.currencyTo.setText(list.get(position).getCurrencyTo());

                holder.amountFrom.setText(list.get(position).getAmountFrom()+"");
                holder.amountTo.setText(list.get(position).getAmountTo()+"");
            }

            @Override
            public int getItemCount() {
                return list.size();
            }

        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return binding.getRoot();
    }

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
            itemView.setOnClickListener(click -> {
                int position = getAbsoluteAdapterPosition();


                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Do you want to delete this history?").setTitle("Question: ")
                        .setPositiveButton("YES", (dialog, cl) -> {

                            //add database
                            Executor thread = Executors.newSingleThreadExecutor();
                            thread.execute(() ->
                            {
                                dao.deleteCurrency(list.get(position));
                                Snackbar.make(currencyFrom, "delete success!",
                                        Snackbar.LENGTH_LONG).show();
                                list.remove(position);
                                getActivity().runOnUiThread(()->myAdapter.notifyItemRemoved(position));
                            });

                            //myAdapter.notifyItemRemoved(position);
                            //myAdapter.notifyItemInserted(position);

                        }).setNegativeButton("NO", (dialog, cl) -> {

                        }).create().show();
            });
        }
    }


}
