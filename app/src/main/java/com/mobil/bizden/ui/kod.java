package com.mobil.bizden.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mobil.bizden.R;

public class kod extends AppCompatActivity {

    ListView listView;

    private String[] toplanma = {"Kizilay", "Çankaya", "Beşevler", "Belediye", "Mecidiye"};
    ListView listView2;
    private String[] kodlar = {"128", "213", "213", "322", "232"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kod);
        listView = findViewById(R.id.listView);
        listView2 = findViewById(R.id.listView2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, kodlar);
        listView2.setAdapter(adapter2);


        ArrayAdapter<String> adapter = new ArrayAdapter<> (this, android.R.layout.simple_list_item_1, toplanma);

        listView.setAdapter(adapter);
    }
}