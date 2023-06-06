package com.mobil.bizden.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mobil.bizden.R;
import com.mobil.bizden.controllers.GatheringAreaController;
import com.mobil.bizden.controllers.GatheringAreaInfoController;
import com.mobil.bizden.controllers.RequestController;
import com.mobil.bizden.models.EntryCode;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.GatheringAreaInfo;
import com.mobil.bizden.models.Request;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class EntryCodeAdapter extends RecyclerView.Adapter<EntryCodeAdapter.ViewHolder> {


    private List<EntryCode> entryCodes;
    public EntryCodeAdapter(List<EntryCode> entryCodes) {
        this.entryCodes = entryCodes;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvOrg, tvTime,tvCode,tvExpiry, tvStatus;


        public ViewHolder(View v) {
            super(v);
            tvName = v.findViewById(R.id.tvAname);
            tvOrg = v.findViewById(R.id.tvAOrg);
            tvTime = v.findViewById(R.id.tvATime);
            tvExpiry = v.findViewById(R.id.tvExpiry);
            tvStatus = v.findViewById(R.id.tvStatus);
            tvCode = v.findViewById(R.id.tvCode);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_code_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@androidx.annotation.NonNull ViewHolder holder, int position) {
        GatheringAreaController gatheringAreaController= new GatheringAreaController();
        EntryCode entryCode= entryCodes.get(position);
        gatheringAreaController.getGatheringArea(entryCode.getAid(), new GatheringAreaController.GatheringAreaCallback() {
            @Override
            public void onGatheringAreaAdded() {

            }

            @Override
            public void onGatheringAreaUpdated() {

            }

            @Override
            public void onGatheringAreaDeleted() {

            }

            @Override
            public void onGatheringAreaLoadFailed(String error) {

            }

            @Override
            public void onGatheringAreaLoaded(GatheringArea gatheringArea) {
                holder.tvName.setText(gatheringArea.getName());

            }

            @Override
            public void onGatheringAreaByLocationLoaded(List<GatheringArea> gatheringAreas) {

            }

            @Override
            public void onCollectionEmpty() {

            }
        });

        GatheringAreaInfoController gatheringAreaInfoController= new GatheringAreaInfoController();
        gatheringAreaInfoController.getGatheringAreaByAid(entryCode.getAid(), new GatheringAreaInfoController.GetCallback() {
            @Override
            public void getSuccessful(GatheringAreaInfo gatheringAreaInfo) {
                holder.tvOrg.setText(gatheringAreaInfo.getOrganization());
            }

            @Override
            public void getFailed(String err) {
                System.out.println(err);
            }
        });

        holder.tvExpiry.setText(entryCode.getValidUntil());
        holder.tvStatus.setTextColor(entryCode.isStatus()? ContextCompat.getColor(holder.tvStatus.getContext(), R.color.valid_green): ContextCompat.getColor(holder.tvStatus.getContext(), R.color.invalid_red) );
        holder.tvCode.setText(entryCode.getCode());
        RequestController requestController= new RequestController();
        requestController.getRequestByDid(entryCode.getDid(), new RequestController.RequestCallback() {
            @Override
            public void onCallback(Request request) {
                holder.tvTime.setText(request.getVolunteerStartTime()+" - "+ request.getVolunteerEndTime());
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e);
            }

            @Override
            public void onRequestsLoaded(List<Request> requests) {

            }

            @Override
            public void onRequestDeleted(String requestId) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
