package com.example.devoir3;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.devoir3.ListAdapter;
import com.example.devoir3.R;
import com.example.devoir3.messages_section.MessagesDirections;
import com.example.devoir3.recherche_section.ResultRechercheArgs;

import static androidx.core.content.ContextCompat.getSystemService;


public class ViewProfile extends Fragment {
    String[] studentName, studentInterest;
    RecyclerView recyclerView;

    public ViewProfile() {
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
        View root = inflater.inflate(R.layout.view_profile, container, false);
        Button message = root.findViewById(R.id.envoyer_message), ami = root.findViewById(R.id.demande_ami),
                partage = root.findViewById(R.id.partager);
        TextView name = root.findViewById(R.id.view_prof_name);
        ImageView profile_img = root.findViewById(R.id.profile_picture);
        String[] profInfo = new String[3];
        if (getArguments() != null) {
            ViewProfileArgs args = ViewProfileArgs.fromBundle(getArguments());
            String[] info = args.getInfo();
            name.setText(info[0]);
            profInfo[2] = info[2];
            profile_img.setImageResource(Integer.parseInt(info[2]));
        }

        message.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            profInfo[0] = name.getText().toString();
            ViewProfileDirections.ActionViewProfileFragmentToConversation action =
                    ViewProfileDirections.actionViewProfileFragmentToConversation(profInfo);
            action.setProfileInfo(profInfo);
            navController.navigate(action);
        });

        partage.setOnClickListener(v -> {
            LayoutInflater inflater1 = (LayoutInflater) root.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View popupView = inflater1.inflate(R.layout.share_popup, null);

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