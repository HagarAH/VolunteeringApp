package com.mobil.bizden.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobil.bizden.adapters.GatheringAreaAdapter;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.GatheringAreaController;
import com.mobil.bizden.controllers.GatheringAreaInfoController;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.controllers.UserLocationController;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.UserLocation;
import com.mobil.bizden.seeders.GatheringAreasSeeder;

import java.util.ArrayList;
import java.util.List;

public class GatheringAreas_frag extends Fragment {
    private RecyclerView recyclerView;
    private GatheringAreaAdapter adapter;
    private GatheringAreaController gatheringAreaController;
    private GatheringAreaInfoController gatheringAreaInfoController;

    // Add a constructor or other methods as necessary

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gathering_areas, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        gatheringAreaController = new GatheringAreaController();
        UserLocationController userLocationController= new UserLocationController();
        GatheringAreaAdapter.OnGatheringAreaClickListener listener= new GatheringAreaAdapter.OnGatheringAreaClickListener() {
            @Override
            public void onGatheringAreaClick(GatheringArea gatheringArea) {
                Requests_frag requests_frag= new Requests_frag();
                Bundle args = new Bundle();
                args.putString("gatheringAreaAid", gatheringArea.getAid());
                requests_frag.setArguments(args);
                FragmentTransaction manager= getParentFragmentManager().beginTransaction();
                manager.replace(R.id.flFragment, requests_frag);
                manager.addToBackStack(null);
                manager.commit();



            }
        };
        UserController userController= new UserController();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new GatheringAreaAdapter(new ArrayList<>(),listener);
        recyclerView.setAdapter(adapter);
        GatheringAreaController.GatheringAreaCallback callback=new GatheringAreaController.GatheringAreaCallback() {
            @Override
            public void onCollectionEmpty() {
                 GatheringAreasSeeder seeder= new GatheringAreasSeeder(gatheringAreaInfoController);
               seeder.seedGatheringAreas();
            }

            @Override
            public void onGatheringAreaAdded() {
                // Gathering area added successfully, do nothing
            }

            @Override
            public void onGatheringAreaUpdated() {
                // Gathering area updated successfully, do nothing
            }

            @Override
            public void onGatheringAreaDeleted() {
                // Gathering area deleted successfully, do nothing
            }

            @Override
            public void onGatheringAreaLoadFailed(String error) {
                // Failed to load gathering areas, display an error message or take appropriate action
            }

            @Override
            public void onGatheringAreaLoaded(GatheringArea gatheringArea) {
                // Gathering area loaded successfully, do nothing
            }

            @Override
            public void onGatheringAreaByLocationLoaded(List<GatheringArea> gatheringAreas) {

                adapter = new GatheringAreaAdapter(gatheringAreas, listener);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        };


        UserLocationController.UserLocationCallback userLocationCallback= new UserLocationController.UserLocationCallback() {
            @Override
            public void onCallback(UserLocation userLocation) {
                gatheringAreaController.isCollectionEmpty(callback);
                gatheringAreaController.getGatheringAreaByLocation(userLocation,callback);
            }

            @Override
            public void onError(Exception e) {
                System.out.println(e);
            }
        };
        userLocationController.getUserLocation(userController.getCurrentUser().getUid(),userLocationCallback);
    }

}
