package com.mobil.bizden.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mobil.bizden.R;
import com.mobil.bizden.controllers.GatheringAreaController;
import com.mobil.bizden.controllers.GatheringAreaInfoController;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.GatheringAreaInfo;
import com.mobil.bizden.models.Request;

import java.util.Collections;
import java.util.List;

public class RequestsAdapter extends RecyclerView.Adapter<RequestsAdapter.ViewHolder> {
    private List<Request> requests;

    public interface RequestsClickListener {
        void onRequestsClick(Request request);
    }

    private RequestsClickListener mListener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName, tvLocation, tvSetTime,btnDelete,creadtedDate,tvStatus,tvAreaOrg;

        public ViewHolder(View v) {
            super(v);
            tvName = v.findViewById(R.id.tvAreaName);
            tvLocation = v.findViewById(R.id.tvAreaLocation);
            tvSetTime= v.findViewById(R.id.tvSetTime);
            btnDelete = v.findViewById(R.id.tvDelete);
            creadtedDate = v.findViewById(R.id.creadtedDate);
            tvStatus = v.findViewById(R.id.tvStatus);
            tvAreaOrg = v.findViewById(R.id.tvAreaOrg);

        }
    }

    public RequestsAdapter(List<Request> request, RequestsClickListener listener) {
        this.requests = request;
        mListener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_requests_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Request request = requests.get(position);
        GatheringAreaController g= new GatheringAreaController();
        g.getGatheringArea(request.getAid(), new GatheringAreaController.GatheringAreaCallback() {
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
                holder.tvLocation.setText(gatheringArea.getDistrict() + "," + gatheringArea.getProvince());
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
        gatheringAreaInfoController.getGatheringAreaByAid(request.getAid(), new GatheringAreaInfoController.GetCallback() {
            @Override
            public void getSuccessful(GatheringAreaInfo gatheringAreaInfo) {
                holder.tvAreaOrg.setText(gatheringAreaInfo.getOrganization());
            }

            @Override
            public void getFailed(String err) {

            }
        });

        holder.tvSetTime.setText(request.getVolunteerStartTime()+" - "+request.getVolunteerEndTime());
        holder.creadtedDate.setText(request.getCreationDate().toDate().toString());
        if(request.isRejection()){
            holder.tvStatus.setText("Reddedildi");
        } else if(request.isAcceptance()){
            holder.tvStatus.setText("Kabul edildi");
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mListener != null) {
                    System.out.println("CLIKSKSKSKKSKSKSKSK");
                    mListener.onRequestsClick(request);
                } else {
                    System.out.println("null app");
                }
            }
        });
    }
    public void setRequests(List<Request> requests) {
        this.requests = requests;
        notifyDataSetChanged();
    }
    public void removeRequestById(String requestId) {
        for (int i = 0; i < requests.size(); i++) {
            Request request = requests.get(i);
            if (request.getDid().equals(requestId)) {
                requests.remove(i);
                notifyItemRemoved(i);
                break;
            }
        }
    }
    @Override
    public int getItemCount() {
        return requests.size();
    }
}
