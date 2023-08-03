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

public class SavedFlightListAdapter extends RecyclerView.Adapter<SavedFlightListAdapter.FightsViewHolder> {

    // SAVEADAPTER-SPECIFIC CLASSES (shared names with FlightListAdapter)
        public interface OnItemClickListener {
            void callBack(FlightEntity flightEntity);
        }

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
    public OnItemClickListener mListener;

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

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setListener(SavedFlightListAdapter.OnItemClickListener mListener) {
        this.mListener = mListener;
    }

}
