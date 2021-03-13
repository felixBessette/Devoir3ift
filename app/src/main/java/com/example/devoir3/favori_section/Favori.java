package com.example.devoir3.favori_section;

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
import android.widget.TextView;

import com.example.devoir3.ListAdapter;
import com.example.devoir3.R;

public class Favori extends Fragment {
    RecyclerView recyclerView;

    public Favori() {
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
        View root = inflater.inflate(R.layout.favori, container, false);
        recyclerView = root.findViewById(R.id.favori_recycler);
        int[] ids = new int[1];
        ids[0] = R.layout.row_favori;
        String[] profInfo = new String[3];
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(getContext());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        ListAdapter myAdapter = new ListAdapter(inflater, ids,
                new ListAdapter.MyAdapterListener() {
            @Override
            public void profileClicked(View v, int info) {
                View val = linearLayoutManager.findViewByPosition(info);
                assert val != null;
                TextView name = val.findViewById(R.id.profile_text1);
                profInfo[0] = name.getTag().toString();
                FavoriDirections.ActionNavigationSavedToViewProfileFragment action =
                        FavoriDirections.actionNavigationSavedToViewProfileFragment(profInfo);
                action.setInfo(profInfo);
                navController.navigate(action);
            }

            @Override
            public void otherClicked(View v, int position) {
                View val = linearLayoutManager.findViewByPosition(position);
                assert val != null;
                TextView name = val.findViewById(R.id.profile_text1);
                ImageButton like = val.findViewById(R.id.likeButton);
                if ((Integer)like.getTag() == Color.RED) {
                    like.setColorFilter(Color.BLACK);
                    like.setTag(Color.BLACK);
                }
                else {
                    like.setColorFilter(Color.RED);
                    like.setTag(Color.RED);
                }
            }
        }, linearLayoutManager,navController);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return root;


    }
}