package com.mobil.bizden.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobil.bizden.R;
import com.mobil.bizden.adapters.EntryCodeAdapter;
import com.mobil.bizden.adapters.EntryCodeAdapter;
import com.mobil.bizden.adapters.GatheringAreaAdapter;
import com.mobil.bizden.controllers.EntryCodeController;
import com.mobil.bizden.controllers.GatheringAreaController;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.models.EntryCode;

import java.util.List;


public class EntryCodes_frag extends Fragment {
    private RecyclerView recyclerView;
    private EntryCodeAdapter adapter;
    UserController userController;
    private EntryCodeController entryCodeController;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entry_codes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        userController= new UserController();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        entryCodeController= new EntryCodeController();
        entryCodeController.getEntryCodesForUser(userController.getCurrentUser().getUid(), new EntryCodeController.EntryCodeCallback() {
           @Override
           public void onEntryCodeAdded(EntryCode entryCode) {

           }

           @Override
           public void onEntryCodeAddError(Exception e) {

           }

           @Override
           public void onEntryCodesLoaded(List<EntryCode> entryCodes) {
               adapter =  new EntryCodeAdapter(entryCodes);
               recyclerView.setAdapter(adapter);
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onEntryCodesLoadError(Exception e) {

           }

           @Override
           public void onEntryCodeDeleted(String entryCodeId) {

           }

           @Override
           public void onEntryCodeDeleteError(Exception e) {

           }
       });






    }
}