package com.mobil.bizden.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mobil.bizden.adapters.RequestsAdapter;
import com.mobil.bizden.R;
import com.mobil.bizden.controllers.EntryCodeController;
import com.mobil.bizden.controllers.RequestController;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.models.Request;

import java.util.ArrayList;
import java.util.List;

public class RequestsView_frag extends Fragment {

    private RecyclerView recyclerView;

    RequestsAdapter adapter;
    public RequestsView_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_requests_view_frag, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RequestsAdapter.RequestsClickListener listener = new RequestsAdapter.RequestsClickListener() {
            @Override
            public void onRequestsClick(Request request) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("İsteği Sil"); // Set the title of the dialog
                builder.setMessage("Katılma isteğinizi silerseniz geri getirilmez.");
                builder.setIcon(Drawable.createFromPath("warning.png"));
                builder.setPositiveButton("Sil", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RequestController requestController = new RequestController();
                        requestController.deleteRequest(request.getDid(), new RequestController.RequestCallback() {
                            @Override
                            public void onCallback(Request request) {
                                // Not needed for deletion
                            }

                            @Override
                            public void onError(Exception e) {
                                Toast.makeText(view.getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onRequestsLoaded(List<Request> requests) {
                                // Not needed for deletion
                            }

                            @Override
                            public void onRequestDeleted(String requestId) {
                                Toast.makeText(view.getContext(), "İstek ve kodu silindi", Toast.LENGTH_SHORT).show();
                                adapter.removeRequestById(requestId);
                                // Notify the adapter of the data change
                                adapter.notifyDataSetChanged();
                                EntryCodeController codeController= new EntryCodeController();
                                codeController.deleteEntryCode(requestId);
                            }
                        });
                    }
                });
                builder.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        };

        recyclerView = view.findViewById(R.id.recyclerViewRequests);
        UserController userController= new UserController();
        RequestController requestController= new RequestController();
        String uid= userController.getCurrentUser().getUid();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RequestsAdapter(new ArrayList<>(),listener);
        recyclerView.setAdapter(adapter);

         requestController.getRequest(uid, new RequestController.RequestCallback() {
            @Override
            public void onCallback(Request request) {

            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onRequestsLoaded(List<Request> requests) {
                adapter.setRequests(requests);
                adapter.notifyDataSetChanged();
            }

             @Override
             public void onRequestDeleted(String requestId) {
                 EntryCodeController codeController= new EntryCodeController();
                 codeController.deleteEntryCode(requestId);

             }
         });




    }

}