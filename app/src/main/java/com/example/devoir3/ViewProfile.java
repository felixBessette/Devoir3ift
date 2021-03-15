package com.example.devoir3;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


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
        Button message = root.findViewById(R.id.envoyer_message), partage = root.findViewById(R.id.partager);
        TextView name = root.findViewById(R.id.view_prof_name), relation_status = root.findViewById(R.id.envoyer_demande_txt),
                interet = root.findViewById(R.id.interet_text),
                cours = root.findViewById(R.id.cours_text),
                clubs = root.findViewById(R.id.clubs_texte);
        ImageView profile_img = root.findViewById(R.id.profile_picture);
        ImageButton like = root.findViewById(R.id.view_prof_like), add_friend = root.findViewById(R.id.demande_ami_button);
        boolean isClickable = true;
        if (getArguments() != null) {
            ViewProfileArgs args = ViewProfileArgs.fromBundle(getArguments());
            String[] info = args.getInfo();
            StudentList.Student student = NavigationMain.studentList.get(Integer.parseInt(info[0]));
            name.setText(student.getName());
            like.setColorFilter(student.getLike());
            profile_img.setImageResource(student.getPic());
            interet.setText(student.interet[0]);
            cours.setText(student.interet[1]);
            clubs.setText(student.interet[2]);

            if (student.relation == 0) {
                relation_status.setText(R.string.remove_friend);
                add_friend.setImageResource(R.drawable.remove);
            }
            if (student.relation == 2) {
                relation_status.setText(R.string.accept);
            }
            if (student.relation == 3) {
                add_friend.setImageResource(R.drawable.pending);
                relation_status.setText(R.string.pending);
            }
            message.setOnClickListener(v -> {
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                ViewProfileDirections.ActionViewProfileFragmentToConversation action =
                        ViewProfileDirections.actionViewProfileFragmentToConversation(info);
                navController.navigate(action);
            });
            like.setOnClickListener(v -> {
                if (student.like == Color.RED) {
                    like.setColorFilter(Color.BLACK);
                    student.like = Color.BLACK;
                }
                else if (student.like == Color.BLACK) {
                    like.setColorFilter(Color.RED);
                    student.like = Color.RED;
                }
            });
            add_friend.setOnClickListener(v -> {
                add_friend.setImageResource(R.drawable.friend_added);
                if (student.relation == 0) {
                    relation_status.setText(R.string.remove_success);
                    student.removeFriend();
                }
                else if (student.relation == 1) {
                    relation_status.setText(R.string.sent);
                    student.relation = 3;
                }
                else if (student.relation == 2) {
                    student.acceptRequest();
                }
            });


        }

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
            ImageButton close = popupView.findViewById(R.id.share_close);
            close.setOnClickListener(u -> {
                popupWindow.dismiss();
            });
        });

        return root;


    }
}