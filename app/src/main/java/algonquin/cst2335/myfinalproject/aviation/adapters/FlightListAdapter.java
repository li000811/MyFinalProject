package algonquin.cst2335.myfinalproject.aviation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.DTO.DataDTO;
import algonquin.cst2335.myfinalproject.aviation.abstractflight.OnItemClickListener;

/**
 * This file will serve as the adapter for
 * populating flight data into the RecyclerView.
 */

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.FlightsViewHolder> {

    static class FlightsViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDate;
        TextView textViewStatus;
        TextView textViewIATACode;
        public FlightsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.date);
            textViewStatus = itemView.findViewById(R.id.status);
            textViewIATACode = itemView.findViewById(R.id.iata);
        }
    }

    private List<DataDTO> mData = new ArrayList<>();
    public OnItemClickListener mListener;

    public void setNewData(List<DataDTO> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aviation_item_flight, parent, false);
        return new FlightsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightsViewHolder holder, int position) {
        DataDTO data = mData.get(position);
        holder.textViewStatus.setText(data.getFlight_status());
        holder.textViewDate.setText(data.getFlight_date());
        holder.textViewIATACode.setText(data.getFlight().getIata());

        holder.itemView.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.callBack(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }
}
