package com.example.devoir3.profile_section;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.devoir3.R;

public class PersonnaliserProfile extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.personnaliser_profile, container, false);
        ImageButton avatar = root.findViewById(R.id.take_pic);
        avatar.setOnClickListener(v -> {
            LayoutInflater inflater1 = (LayoutInflater) root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = inflater1.inflate(R.layout.camera_popup, null);

            //Specify the length and width through constants
            int width = LinearLayout.LayoutParams.MATCH_PARENT;
            int height = LinearLayout.LayoutParams.MATCH_PARENT;

            //Make Inactive Items Outside Of PopupWindow
            boolean focusable = true;

            //Create a window with our parameters
            final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

            //Set the location of the window on the screen
            popupWindow.showAtLocation(root, Gravity.CENTER, 400, 400);
            ImageButton close = popupView.findViewById(R.id.close);
            close.setOnClickListener(u -> {
                popupWindow.dismiss();
            });
        });
        return root;
    }
}