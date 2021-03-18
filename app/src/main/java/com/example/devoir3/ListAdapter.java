package com.example.devoir3;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devoir3.favori_section.FavoriDirections;
import com.example.devoir3.messages_section.MessagesDirections;
import com.example.devoir3.notif_section.NotifDirections;
import com.example.devoir3.profile_section.ContactDirections;
import com.example.devoir3.recherche_section.ResultRechercheDirections;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    LayoutInflater inflater;
    int id;
    ArrayList<Integer> studentIds;
    LinearLayoutManager manager;
    NavController navController;
    String[] convo1;
    String[] convo2;

    public ListAdapter(LayoutInflater layoutInflater, int ids,
                       LinearLayoutManager linearLayoutManager, NavController navcont) {
        inflater = layoutInflater;
        id = ids;
        studentIds = favoriteStudent();
        manager = linearLayoutManager;
        navController = navcont;
    }

    public ArrayList<Integer> favoriteStudent() {
        ArrayList<Integer> ids = new ArrayList<>();
        if (id == R.layout.row_favori) {
            for (int i = 0; i < NavigationMain.studentList.size(); i++) {
                if (NavigationMain.studentList.get(i).like == Color.RED) {
                    ids.add(i);
                }
            }
        }
        else if (id == R.layout.row_notif) {
            for (int i = 0; i < NavigationMain.studentList.size(); i++) {
                if (NavigationMain.studentList.get(i).relation == 2) {
                    ids.add(i);
                }
            }
        }
        else if (id == R.layout.row_contacts || id == R.layout.row_messages) {
            for (int i = 0; i < NavigationMain.studentList.size(); i++) {
                if (NavigationMain.studentList.get(i).relation == 0) {
                    ids.add(i);
                }
            }
        }
        else {
            for (int i = 0; i < NavigationMain.studentList.size(); i++) {
                ids.add(i);
            }
        }
        return ids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(id, parent, false);
        convo1 = view.getResources().getStringArray(R.array.convo1);
        convo2 = view.getResources().getStringArray(R.array.convo2);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentList.Student student = NavigationMain.studentList.get(studentIds.get(position));
        String name = student.name;
        String interet = student.interet[0];
        if (id == R.layout.row_convo) {
            name = convo1[position];
            interet = convo2[position];
            holder.profileDescrip.setText(interet);
        }
        else {
            holder.profile_pic.setImageResource(student.picture);
        }
        holder.profileName.setText(name);
        holder.profileName.setTag(studentIds.get(position));
        if (id != R.layout.row_messages && id != R.layout.row_convo && id != R.layout.row_notif) {
            holder.likeButton.setColorFilter(student.like);
            if (id == R.layout.row_result) {
                holder.profileDescrip.setText(interet);
            }
            else {
                holder.profileDescrip.setText(student.etude);
            }
        }
    }



    @Override
    public int getItemCount() {
        return studentIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView profileName, profileDescrip, accept, refuse;
        ImageView profile_pic;
        public Button viewProfile;
        ImageButton likeButton;
        ImageButton refuseButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileName = itemView.findViewById(R.id.profile_text1);
            profileDescrip = itemView.findViewById(R.id.profile_text2);
            if (id == R.layout.row_notif) {
                accept = itemView.findViewById(R.id.notif_friends_accept);
                refuse = itemView.findViewById(R.id.friends_notif_refuse);
                refuseButton = itemView.findViewById(R.id.refuse_request);
                refuseButton.setOnClickListener(v -> {
                    View val = manager.findViewByPosition(getAdapterPosition());
                    assert val != null;
                    TextView name = val.findViewById(R.id.profile_text1);
                    StudentList.Student student = NavigationMain.studentList.get((int)name.getTag());
                    likeButton.setVisibility(View.INVISIBLE);
                    refuse.setVisibility(View.INVISIBLE);
                    accept.setVisibility(View.INVISIBLE);
                    student.removeFriend();
                    refuseButton.setImageResource(R.drawable.friend_added);
                });
            }
            if (id != R.layout.row_convo && id != R.layout.row_messages) {
                likeButton = itemView.findViewById(R.id.likeButton);
                likeButton.setOnClickListener(v -> {
                    View val = manager.findViewByPosition(getAdapterPosition());
                    assert val != null;
                    TextView name = val.findViewById(R.id.profile_text1);
                    StudentList.Student student = NavigationMain.studentList.get((int)name.getTag());
                    if (id == R.layout.row_notif) {
                        likeButton.setImageResource(R.drawable.friend_added);
                        refuseButton.setVisibility(View.INVISIBLE);
                        refuse.setVisibility(View.INVISIBLE);
                        accept.setVisibility(View.INVISIBLE);
                        student.acceptRequest();
                    }
                    else {
                        if (student.like == Color.RED) {
                            student.like = Color.BLACK;
                            likeButton.setColorFilter(Color.BLACK);
                        } else if (student.like == Color.BLACK) {
                            student.like = Color.RED;
                            likeButton.setColorFilter(Color.RED);
                        }
                    }
                });
            }
            if (id != R.layout.row_convo) {
                viewProfile = itemView.findViewById(R.id.profile_button);
                profile_pic = itemView.findViewById(R.id.profile_img);
                viewProfile.setOnClickListener(v -> {
                    String[] profInfo = new String[1];
                    View val = manager.findViewByPosition(getAdapterPosition());
                    assert val != null;
                    TextView name = val.findViewById(R.id.profile_text1);
                    profInfo[0] = name.getTag().toString();
                    if (id == R.layout.row_favori) {
                        FavoriDirections.ActionNavigationSavedToViewProfileFragment action =
                                FavoriDirections.actionNavigationSavedToViewProfileFragment(profInfo);
                        navController.navigate(action);
                    }
                    else if (id == R.layout.row_notif) {
                        NotifDirections.ActionNavigationNotifToViewProfileFragment action =
                                NotifDirections.actionNavigationNotifToViewProfileFragment(profInfo);
                        navController.navigate(action);
                    }
                    else if (id == R.layout.row_contacts) {
                        ContactDirections.ActionContact2ToViewProfileFragment action =
                                ContactDirections.actionContact2ToViewProfileFragment(profInfo);
                        navController.navigate(action);
                    }
                    else if (id== R.layout.row_messages) {
                        MessagesDirections.ActionNavigationMessagesToConversation action =
                                MessagesDirections.actionNavigationMessagesToConversation(profInfo);
                        navController.navigate(action);
                    }
                    else if (id== R.layout.row_result) {
                        ResultRechercheDirections.ActionResultFragmentToViewProfileFragment action =
                                ResultRechercheDirections.actionResultFragmentToViewProfileFragment(profInfo);
                        navController.navigate(action);
                    }

                });
            }
        }
    }
}
