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

public class SearchFlightListAdapter extends RecyclerView.Adapter<SearchFlightListAdapter.FightsViewHolder> {

    // FLIGHTLISTADAPTER-SPECIFIC CLASSES (shared names)
        public interface OnItemClickListener {
            void callBack(DataDTO dataDTO);
        }

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
    public FightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aviation_item_flight, parent, false);
        return new FightsViewHolder(view);
    }

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

    public void setListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

}
