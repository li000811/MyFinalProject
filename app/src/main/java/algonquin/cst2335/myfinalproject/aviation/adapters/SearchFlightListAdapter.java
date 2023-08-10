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

/**
 * The `SearchFlightListAdapter` class is responsible for adapting a list of flight data
 * into a RecyclerView that displays these flight details in the UI.
 */
public class SearchFlightListAdapter extends RecyclerView.Adapter<SearchFlightListAdapter.FightsViewHolder> {

    /**
     * Interface definition for a callback to be invoked when a flight data is clicked.
     */
    public interface OnItemClickListener {
        /**
         * Called when a flight data is clicked.
         *
         * @param dataDTO The clicked flight data.
         */
        void callBack(DataDTO dataDTO);
    }

    /**
     * ViewHolder class to hold views for each item in the RecyclerView.
     */
    static class FightsViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate;
        TextView tvStatus;
        TextView tvIata;

        public FightsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.date);
            tvStatus = itemView.findViewById(R.id.status);
            tvIata = itemView.findViewById(R.id.iata);
        }
    }

    private List<DataDTO> mData = new ArrayList<>();
    private OnItemClickListener mListener;

    /**
     * Set new data for the adapter and update the UI.
     *
     * @param data The new list of flight data to display.
     */
    public void setNewData(List<DataDTO> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aviation_item_flight, parent, false);
        return new FightsViewHolder(view);
    }

    /**
     * Binds the data to the ViewHolder at the specified position.
     *
     * @param holder   The ViewHolder to bind data to.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull FightsViewHolder holder, int position) {
        DataDTO data = mData.get(position);
        holder.tvStatus.setText(data.getFlight_status());
        holder.tvDate.setText(data.getFlight_date());
        holder.tvIata.setText(data.getFlight().getIata());

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

    /**
     * Set the listener to be called when a flight data is clicked.
     *
     * @param mListener The listener to set.
     */
    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }
}

