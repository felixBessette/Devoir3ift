package com.example.devoir3.recherche_section;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.devoir3.NavigationMain;
import com.example.devoir3.R;

public class Recherche extends Fragment {
    FrameLayout resultContainer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.recherche, container, false);
        Button b1 = root.findViewById(R.id.interet), b2 = root.findViewById(R.id.cours), b3 = root.findViewById(R.id.club);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        RechercheDirections.ActionNavigationRechercheToResultFragment action = RechercheDirections.actionNavigationRechercheToResultFragment();
        b1.setOnClickListener((View v) -> {
            action.setMessage("interet");
            navController.navigate(action);
        });
        b2.setOnClickListener((View v) -> {
            action.setMessage("cours");
            navController.navigate(action);
        });
        b3.setOnClickListener((View v) -> {
            action.setMessage("clubs");
            navController.navigate(action);
        });
        return root;
    }
}