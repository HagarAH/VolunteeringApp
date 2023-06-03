package com.mobil.bizden.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobil.bizden.R;
import com.mobil.bizden.controllers.GatheringAreaController;
import com.mobil.bizden.controllers.UserController;
import com.mobil.bizden.controllers.UserLocationController;
import com.mobil.bizden.models.GatheringArea;
import com.mobil.bizden.models.TimeRequirement;
import com.mobil.bizden.models.UserLocation;

import java.util.ArrayList;
import java.util.List;

public class GatheringAreas_frag extends Fragment {
    private RecyclerView recyclerView;
    private GatheringAreaAdapter adapter;
    private GatheringAreaController gatheringAreaController;

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
        UserController userController= new UserController();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new GatheringAreaAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        GatheringAreaController.GatheringAreaCallback callback=new GatheringAreaController.GatheringAreaCallback() {
            @Override
            public void onCollectionEmpty() {
                // The gatheringAreas collection is empty, seed the gathering areas
                seedGatheringAreas();
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
                System.out.println(gatheringAreas.get(2).getName());
                adapter = new GatheringAreaAdapter(gatheringAreas);
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
    public void seedGatheringAreas() {
        List<TimeRequirement> timeRequirements1 = new ArrayList<>();
        timeRequirements1.add(new TimeRequirement("09:00", "13:00", 20));
        timeRequirements1.add(new TimeRequirement("14:00", "18:00", 15));

        List<TimeRequirement> timeRequirements2 = new ArrayList<>();
        timeRequirements2.add(new TimeRequirement("10:00", "14:00", 25));
        timeRequirements2.add(new TimeRequirement("15:00", "19:00", 20));

        List<TimeRequirement> timeRequirements3 = new ArrayList<>();
        timeRequirements3.add(new TimeRequirement("08:00", "12:00", 30));
        timeRequirements3.add(new TimeRequirement("13:00", "17:00", 25));

        GatheringArea gatheringArea1 = new GatheringArea(
                "Area1",
                "Gezi Parkı",
                150,
                "İstanbul",
                "Beyoğlu",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea2 = new GatheringArea(
                "Area2",
                "Kuğulu Parkı",
                200,
                "Ankara",
                "Çankaya",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea3 = new GatheringArea(
                "Area3",
                "Düden Parkı",
                250,
                "Antalya",
                "Muratpaşa",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea4 = new GatheringArea(
                "Area4",
                "Kent Parkı",
                300,
                "Eskişehir",
                "Odunpazarı",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea5 = new GatheringArea(
                "Area5",
                "Atatürk Parkı",
                350,
                "Bursa",
                "Osmangazi",
                "Açık",
                timeRequirements2
        );
        GatheringArea gatheringArea6 = new GatheringArea(
                "Area6",
                "Gençlik Merkezi Parkı",
                400,
                "İzmir",
                "Konak",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea7 = new GatheringArea(
                "Area7",
                "Sakarya Gençlik Merkezi",
                450,
                "Sakarya",
                "Serdivan",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea8 = new GatheringArea(
                "Area8",
                "Mersin Gençlik Merkezi Parkı",
                500,
                "Mersin",
                "Akdeniz",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea9 = new GatheringArea(
                "Area9",
                "Trabzon Gençlik Merkezi",
                550,
                "Trabzon",
                "Ortahisar",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea10 = new GatheringArea(
                "Area10",
                "Kocaeli Gençlik Merkezi Parkı",
                600,
                "Kocaeli",
                "İzmit",
                "Açık",
                timeRequirements1
        );
        GatheringArea gatheringArea11 = new GatheringArea(
                "Area11",
                "Gülhane Parkı",
                300,
                "İstanbul",
                "Eminönü",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea12 = new GatheringArea(
                "Area12",
                "Çankaya Gençlik Merkezi",
                350,
                "Ankara",
                "Çankaya",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea13 = new GatheringArea(
                "Area13",
                "Lara Gençlik Merkezi Parkı",
                400,
                "Antalya",
                "Muratpaşa",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea14 = new GatheringArea(
                "Area14",
                "Kent Meydanı Parkı",
                450,
                "Eskişehir",
                "Tepebaşı",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea15 = new GatheringArea(
                "Area15",
                "Orhangazi Gençlik Merkezi",
                500,
                "Bursa",
                "Nilüfer",
                "Açık",
                timeRequirements3
        );
        GatheringArea gatheringArea16 = new GatheringArea(
                "Area16",
                "Atatürk Gençlik Merkezi Parkı",
                550,
                "İzmir",
                "Karşıyaka",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea17 = new GatheringArea(
                "Area17",
                "Denizli Gençlik Merkezi",
                600,
                "Denizli",
                "Merkezefendi",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea18 = new GatheringArea(
                "Area18",
                "Adana Gençlik Merkezi Parkı",
                650,
                "Adana",
                "Seyhan",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea19 = new GatheringArea(
                "Area19",
                "Göztepe Parkı",
                700,
                "İstanbul",
                "Kadıköy",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea20 = new GatheringArea(
                "Area20",
                "Konya Gençlik Merkezi",
                750,
                "Konya",
                "Selçuklu",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea21 = new GatheringArea(
                "Area21",
                "Antalya Gençlik Merkezi Parkı",
                800,
                "Antalya",
                "Kepez",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea22 = new GatheringArea(
                "Area22",
                "Bolu Gençlik Merkezi",
                850,
                "Bolu",
                "Merkez",
                "Açık",
                timeRequirements1
        );
        GatheringArea gatheringArea23 = new GatheringArea(
                "Area4",
                "Yenimahalle Parkı",
                300,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea24 = new GatheringArea(
                "Area5",
                "Gençlik Merkezi Parkı",
                350,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements2
        );
        GatheringArea gatheringArea25 = new GatheringArea(
                "Area6",
                "Atatürk Ormanı",
                400,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea26 = new GatheringArea(
                "Area7",
                "Gazi Ormanı",
                450,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements1
        );
        GatheringArea gatheringArea27 = new GatheringArea(
                "Area8",
                "Kurtuluş Parkı",
                500,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea28 = new GatheringArea(
                "Area9",
                "Ümitköy Parkı",
                550,
                "Ankara",
                "Yenimahalle",
                "Açık",
                timeRequirements3
        );
        GatheringArea gatheringArea29 = new GatheringArea(
                "Area10",
                "Kurtuluş Parkı",
                400,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea30 = new GatheringArea(
                "Area11",
                "Gençlik Merkezi Parkı",
                450,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements2
        );
        GatheringArea gatheringArea31 = new GatheringArea(
                "Area12",
                "Hüseyin Gazi Parkı",
                500,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea32 = new GatheringArea(
                "Area13",
                "Sıhhiye Parkı",
                550,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements1
        );
        GatheringArea gatheringArea33 = new GatheringArea(
                "Area14",
                "Ulucanlar Cezaevi Müzesi Parkı",
                600,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea34 = new GatheringArea(
                "Area15",
                "Hacı Bayram Veli Camii Yanı Park",
                650,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements3
        );

        GatheringArea gatheringArea35 = new GatheringArea(
                "Area16",
                "Roma Hamamı Parkı",
                700,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements1
        );

        GatheringArea gatheringArea36 = new GatheringArea(
                "Area17",
                "Gençlik Parkı",
                750,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements2
        );

        GatheringArea gatheringArea37 = new GatheringArea(
                "Area18",
                "Ankara Kalesi Parkı",
                800,
                "Ankara",
                "Altındağ",
                "Açık",
                timeRequirements3
        );



        // Initialize an instance of the class that has addGatheringArea method
        GatheringAreaController gatheringArea = new GatheringAreaController();

        GatheringAreaController.GatheringAreaCallback gatheringAreaCallback = new GatheringAreaController.GatheringAreaCallback() {
            @Override
            public void onGatheringAreaAdded() {
                System.out.println("Gathering Area Added Successfully");
            }

            @Override
            public void onGatheringAreaUpdated() {
                System.out.println("Gathering Area Updated Successfully");
            }

            @Override
            public void onGatheringAreaDeleted() {
                System.out.println("Gathering Area Deleted Successfully");
            }

            @Override
            public void onGatheringAreaLoadFailed(String error) {
                System.out.println("Failed to Load Gathering Area: " + error);
            }

            @Override
            public void onGatheringAreaLoaded(GatheringArea gatheringArea) {
                System.out.println("Gathering Area Loaded Successfully: " + gatheringArea.getName());
            }

            @Override
            public void onGatheringAreaByLocationLoaded(List<GatheringArea> gatheringAreas) {

            }

            @Override
            public void onCollectionEmpty() {

            }
        };

        gatheringArea.addGatheringArea(gatheringArea1, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea2, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea3, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea4, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea5, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea6, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea7, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea8, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea9, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea10, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea11, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea12, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea13, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea14, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea15, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea16, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea17, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea18, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea19, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea20, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea21, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea22, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea23, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea24, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea25, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea26, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea27, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea28, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea29, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea30, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea31, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea32, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea33, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea34, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea35, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea36, gatheringAreaCallback);
        gatheringArea.addGatheringArea(gatheringArea37, gatheringAreaCallback);

    }

}
