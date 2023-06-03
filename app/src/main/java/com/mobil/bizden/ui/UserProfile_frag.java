package com.mobil.bizden.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mobil.bizden.R;
import com.mobil.bizden.databinding.FragmentResetPassBinding;

import org.checkerframework.checker.nullness.qual.NonNull;


public class UserProfile_frag extends Fragment {

    private ImageView imageViewsettings;
    private Button button;

    private FragmentResetPassBinding binding;
    public UserProfile_frag(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile_frag, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        button = view.findViewById(R.id.updateButton);
        imageViewsettings = view.findViewById(R.id.imageViewsettings);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), updateProfile.class);
                startActivity(intent);
            }
        });
    }

}