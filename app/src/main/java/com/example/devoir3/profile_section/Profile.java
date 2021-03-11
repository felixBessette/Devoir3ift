package com.example.devoir3.profile_section;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.devoir3.R;

public class Profile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.profile, container, false);
        Button contact = root.findViewById(R.id.contact), param = root.findViewById(R.id.param),
                edit = root.findViewById(R.id.personnaliser);

        contact.setOnClickListener(v -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_profil_to_contact2);
        });
        edit.setOnClickListener(v -> {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.action_navigation_profil_to_personnaliserProfile);
        });
        return root;
    }
}