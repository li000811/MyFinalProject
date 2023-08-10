package algonquin.cst2335.myfinalproject.aviation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

import algonquin.cst2335.myfinalproject.R;
import algonquin.cst2335.myfinalproject.aviation.entities.FlightEntity;

/**
 * The `SavedFlightListAdapter` class is responsible for adapting a list of saved flight entities
 * into a RecyclerView that displays these entities in the UI.
 */
public class SavedFlightListAdapter extends RecyclerView.Adapter<SavedFlightListAdapter.FightsViewHolder> {

    /**
     * Interface definition for a callback to be invoked when a saved flight entity is clicked.
     */
    public interface OnItemClickListener {
        /**
         * Called when a saved flight entity is clicked.
         *
         * @param flightEntity The clicked flight entity.
         */
        void callBack(FlightEntity flightEntity);
    }

    /**
     * ViewHolder class to hold views for each item in the RecyclerView.
     */
    public static class FightsViewHolder extends RecyclerView.ViewHolder {
        TextView tvNo;
        TextView tvData;
        Button btnDelete;

        public FightsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNo = itemView.findViewById(R.id.tv_no);
            tvData = itemView.findViewById(R.id.tv_data);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }

    private List<FlightEntity> mData = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mListener;

    /**
     * Set new data for the adapter and update the UI.
     *
     * @param data The new list of flight entities to display.
     */
    public void setNewData(List<FlightEntity> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aviation_item_saved, parent, false);

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
        FlightEntity data = mData.get(position);
        holder.tvNo.setText("No." + data.id);
        String str =
                mContext.getString(R.string.iata) + ":" + data.iata +
                        "\n" + mContext.getString(R.string.status) + ":" + data.status +
                        "\n" + mContext.getString(R.string.destination) + ":" + data.Destination +
                        "\n" + mContext.getString(R.string.terminal) + ":" + data.Terminal +
                        "\n" + mContext.getString(R.string.gate) + ":" + data.Gate +
                        "\n" + mContext.getString(R.string.delay) + ":" + data.Delay;
        holder.tvData.setText(str);

        holder.btnDelete.setOnClickListener(view -> {
            if (mListener != null) {
                mListener.callBack(data);
            }
        });
    }

    /**
     * Returns the total number of items in the adapter's data set.
     *
     * @return The total number of items.
     */
    @Override
    public int getItemCount() {
        return mData.size();
    }

    /**
     * Set the listener to be called when a saved flight entity is clicked.
     *
     * @param mListener The listener to set.
     */
    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }
}
