package com.mobil.bizden.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mobil.bizden.R;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.GatheringAreaInfo;

import java.util.List;

public class GatheringAreaAdapter extends RecyclerView.Adapter<GatheringAreaAdapter.ViewHolder> {
    private List<GatheringArea> gatheringAreas;
    public interface OnGatheringAreaClickListener {
        void onGatheringAreaClick(GatheringArea gatheringArea);
    }
    private OnGatheringAreaClickListener mListener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvLocation, tvCapacity;
        public Button btnSignUp;

        public ViewHolder(View v) {
            super(v);
            tvName = v.findViewById(R.id.tvName);
            tvLocation = v.findViewById(R.id.tvLocation);
            tvCapacity = v.findViewById(R.id.tvCapacity);
            btnSignUp = v.findViewById(R.id.btnSignUp);
        }
    }

    public GatheringAreaAdapter(List<GatheringArea> gatheringAreas,OnGatheringAreaClickListener listener) {
        this.gatheringAreas = gatheringAreas;
        mListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gathering_area, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GatheringArea gatheringArea = gatheringAreas.get(position);
        holder.tvName.setText(gatheringArea.getName());
        holder.tvLocation.setText(gatheringArea.getDistrict() + "," + gatheringArea.getProvince());
        holder.tvCapacity.setText(String.valueOf(gatheringArea.getCapacity()));
        holder.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListener != null) {
                    System.out.println("CLIKSKSKSKKSKSKSKSK");
                    mListener.onGatheringAreaClick(gatheringArea);
                }
                else{
                    System.out.println("null app");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gatheringAreas.size();
    }
}
