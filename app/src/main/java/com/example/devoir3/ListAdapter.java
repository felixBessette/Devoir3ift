package com.example.devoir3;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devoir3.favori_section.FavoriDirections;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    LayoutInflater inflater;
    int[] id;
    ArrayList<Integer> studentIds;
    public MyAdapterListener onClickListener;
    LinearLayoutManager manager;
    NavController navController;

    public interface MyAdapterListener {

        void profileClicked(View v, int position);
        void otherClicked(View v, int position);
    }

    public ListAdapter(LayoutInflater layoutInflater, int[] ids,
                       MyAdapterListener myAdapterListener, LinearLayoutManager linearLayoutManager, NavController navcont) {
        inflater = layoutInflater;
        onClickListener = myAdapterListener;
        id = ids;
        studentIds = favoriteStudent();
        manager = linearLayoutManager;
        navController = navcont;
    }

    public ArrayList<Integer> favoriteStudent() {
        ArrayList<Integer> ids = new ArrayList<>();
        if (id[0] == R.layout.row_favori) {
            for (int i = 0; i < NavigationMain.studentList.size(); i++) {
                if (NavigationMain.studentList.get(i).getLike() == Color.RED) {
                    ids.add(i);
                }
            }
        }
        else {
            for (int i = 0; i < NavigationMain.studentList.size(); i++) {
                ids.add(i);
            }
        }
        System.out.println(ids);
        return ids;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(id[0], parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentList.Student student = NavigationMain.studentList.get(studentIds.get(position));
        holder.profileName.setText(student.getName());
        holder.profileName.setTag(student.getId());
        if (id[0] != R.layout.row_convo) {
            holder.profile_pic.setImageResource(student.getPic());
        }
        if (id[0] != R.layout.row_messages && id[0] != R.layout.row_notif) {
            holder.profileDescrip.setText(student.getInteret());
        }
        if (id[0] != R.layout.row_messages && id[0] != R.layout.row_convo && id[0] != R.layout.row_notif) {
                holder.likeButton.setColorFilter(student.getLike());
        }
    }



    @Override
    public int getItemCount() {
        return studentIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView profileName, profileDescrip;
        ImageView profile_pic;
        public Button viewProfile;
        ImageButton likeButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profileName = itemView.findViewById(R.id.profile_text1);
            profileDescrip = itemView.findViewById(R.id.profile_text2);
            if (id[0] != R.layout.row_convo && id[0] != R.layout.row_messages) {
                likeButton = itemView.findViewById(R.id.likeButton);
                likeButton.setOnClickListener(v -> onClickListener.otherClicked(v, getAdapterPosition()));
            }
            if (id[0] != R.layout.row_convo) {
                viewProfile = itemView.findViewById(R.id.profile_button);
                profile_pic = itemView.findViewById(R.id.profile_img);
                viewProfile.setOnClickListener(v -> {
                    String[] profInfo = new String[1];
                    View val = manager.findViewByPosition(getAdapterPosition());
                    assert val != null;
                    TextView name = val.findViewById(R.id.profile_text1);
                    if (id[0] == R.layout.row_favori) {
                        profInfo[0] = name.getTag().toString();
                        FavoriDirections.ActionNavigationSavedToViewProfileFragment action =
                                FavoriDirections.actionNavigationSavedToViewProfileFragment(profInfo);
                        action.setInfo(profInfo);
                        navController.navigate(action);
                    }

                });
            }
        }
    }
}
