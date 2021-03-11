package com.example.devoir3.notif_section;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devoir3.ListAdapter;
import com.example.devoir3.R;

public class Notif extends Fragment {
    String[] studentName, studentInterest;
    RecyclerView recyclerView;

    public Notif() {
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
        View root = inflater.inflate(R.layout.notif, container, false);
        recyclerView = root.findViewById(R.id.notif_recycler);
        studentName = getResources().getStringArray(R.array.student_name);
        studentInterest = getResources().getStringArray(R.array.student_interet);
        int[] ids = new int[3];
        ids[0] = R.layout.row_notif;
        ids[1] = R.id.likeButton;
        ids[2] = -1;
        String[] profInfo = new String[3];
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(getContext());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        ListAdapter myAdapter = new ListAdapter(inflater, ids,
                new ListAdapter.MyAdapterListener() {
            @Override
            public void profileClicked(View v, int position) {
                View val = linearLayoutManager.findViewByPosition(position);
                assert val != null;
                TextView name = val.findViewById(R.id.profile_text1);
                ImageView imgView = val.findViewById(R.id.profile_img);
                profInfo[0] = name.getText().toString();
                profInfo[2] = imgView.getTag().toString();
                NotifDirections.ActionNavigationNotifToViewProfileFragment action =
                        NotifDirections.actionNavigationNotifToViewProfileFragment(profInfo);
                action.setInfo(profInfo);
                navController.navigate(action);
            }

            @Override
            public void otherClicked(View v, int position) {
                View val = linearLayoutManager.findViewByPosition(position);
                assert val != null;
                ImageButton add = val.findViewById(R.id.likeButton);
                add.setImageResource(R.drawable.friend_added);
                TextView status = val.findViewById(R.id.friend_status);
                status.setText(null);
            }
        });
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return root;


    }
}