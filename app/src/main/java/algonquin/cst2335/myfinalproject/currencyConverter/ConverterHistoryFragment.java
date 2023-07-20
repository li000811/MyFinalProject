package algonquin.cst2335.myfinalproject.currencyConverter;

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

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.databinding.FragmentConverterHistoryBinding;
import algonquin.cst2335.myfinalproject.databinding.ItemConverterHistoryBinding;

public class ConverterHistoryFragment extends Fragment {
    FragmentConverterHistoryBinding binding;

    private RecyclerView.Adapter myAdapter;

    public ConverterHistoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        binding = FragmentConverterHistoryBinding.inflate(inflater);
        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {
            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                ItemConverterHistoryBinding sendBinding = ItemConverterHistoryBinding.inflate(getLayoutInflater(), parent, false);
                return new MyRowHolder(sendBinding.getRoot());
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {

                holder.currencyFrom.setText("USD");
                holder.currencyTo.setText("CAD");

                holder.amountFrom.setText("123.00");
                holder.amountTo.setText("162.36");
            }

            @Override
            public int getItemCount() {
                return 10;
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

                            myAdapter.notifyItemRemoved(position);
                            myAdapter.notifyItemInserted(position);
                        }).setNegativeButton("NO", (dialog, cl) -> {

                        }).create().show();
            });
        }
    }


}
