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
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    String[] studentName, studentInterest;
    LayoutInflater inflater;
    int[] id;
    int[] avatar;

    public MyAdapterListener onClickListener;

    public interface MyAdapterListener {

        void profileClicked(View v, int position);
        void otherClicked(View v, int position);
    }

    public ListAdapter(LayoutInflater layoutInflater, int[] ids,
                       MyAdapterListener myAdapterListener) {
        inflater = layoutInflater;
        onClickListener = myAdapterListener;
        id = ids;
    }
    void shuffleArray1(int[] arr) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
    void shuffleArray2(String[] arr) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = arr.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = arr[index];
            arr[index] = arr[i];
            arr[i] = a;
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(id[0], parent, false);
        avatar = new int[]{R.drawable.avatar1, R.drawable.avatar2, R.drawable.avatar3, R.drawable.avatar7,
                R.drawable.avatar8, R.drawable.avatar9, R.drawable.avatar10, R.drawable.avatar11, R.drawable.avatar12};
        if (id[0] == R.layout.row_convo) {
            studentName = view.getResources().getStringArray(R.array.convo1);
            studentInterest = view.getResources().getStringArray(R.array.convo2);
        }
        else {
            studentName = view.getResources().getStringArray(R.array.student_name);
            studentInterest = view.getResources().getStringArray(R.array.student_interet);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.profileName.setText(studentName[position]);
        if (id[0] != R.layout.row_convo) {
            holder.profile_pic.setImageResource(avatar[position]);
            holder.profile_pic.setTag(avatar[position]);
        }
        if (id[0] != R.layout.row_messages && id[0] != R.layout.row_notif) {
            holder.profileDescrip.setText(studentInterest[position]);
        }
        if (id[0] != R.layout.row_messages && id[0] != R.layout.row_convo && id[0] != R.layout.row_notif) {
            double random = Math.random();
            if (random < 0.5) {
                holder.likeButton.setColorFilter(Color.RED);
                holder.likeButton.setTag(Color.RED);
            }
            else {
                holder.likeButton.setColorFilter(Color.BLACK);
                holder.likeButton.setTag(Color.BLACK);
            }
        }
    }


    @Override
    public int getItemCount() {
        return 9;
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
                viewProfile.setOnClickListener(v -> onClickListener.profileClicked(v, getAdapterPosition()));
            }
        }
    }
}
