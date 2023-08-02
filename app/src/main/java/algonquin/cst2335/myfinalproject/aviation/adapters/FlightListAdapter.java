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

/**
 * This file will serve as the adapter for
 * populating flight data into the RecyclerView.
 */
public class FlightListAdapter /*extends RecyclerView.Adapter<FlightListAdapter.>*/ {

//    private List<FightsBean.DataDTO> mData = new ArrayList<>();
//
//    public void setNewData(List<FightsBean.DataDTO> data) {
//        mData.clear();
//        if (data != null) {
//            mData.addAll(data);
//        }
//
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public FightsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_fight, parent, false);
//        return new FightsViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FightsViewHolder holder, int position) {
//        FightsBean.DataDTO data = mData.get(position);
//        holder.tvStatus.setText(data.getFlight_status());
//        holder.tvDate.setText(data.getFlight_date());
//        holder.tvIata.setText(data.getFlight().getIata());
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mListener != null) {
//                    mListener.callBack(data);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return mData.size();
//    }
//
//    public static class FightsViewHolder extends RecyclerView.ViewHolder {
//        TextView tvDate;
//        TextView tvStatus;
//        TextView tvIata;
//        public FightsViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvDate = itemView.findViewById(R.id.tv_date);
//            tvStatus = itemView.findViewById(R.id.tv_status);
//            tvIata = itemView.findViewById(R.id.tv_iata);
//        }
//    }
//
//    public onItemClickListener mListener;
//
//    public void setListener(onItemClickListener mListener) {
//        this.mListener = mListener;
//    }
//
//    public interface onItemClickListener{
//        void callBack(FightsBean.DataDTO dataDTO);
//    }

}
