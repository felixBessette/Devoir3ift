package com.example.devoir3.profile_section;

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
import com.example.devoir3.favori_section.FavoriDirections;


public class Contact extends Fragment {
    String[] studentName, studentInterest;
    int img = R.drawable.avatar;
    RecyclerView recyclerView;

    public Contact() {
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
        View root = inflater.inflate(R.layout.contact, container, false);
        recyclerView = root.findViewById(R.id.contact_recycler);
        String[] profInfo = new String[3];
        int[] ids = new int[3];
        ids[0] = R.layout.row_contacts;
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(getContext());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        ListAdapter myAdapter = new ListAdapter(inflater, ids,
                new ListAdapter.MyAdapterListener() {
                    @Override
                    public void profileClicked(View v, int info) {
                        View val = linearLayoutManager.findViewByPosition(info);
                        assert val != null;
                        TextView name = val.findViewById(R.id.profile_text1);
                        ImageView imgView = val.findViewById(R.id.profile_img);
                        profInfo[0] = name.getText().toString();
                        profInfo[2] = imgView.getTag().toString();
                        ContactDirections.ActionContact2ToViewProfileFragment action =
                                ContactDirections.actionContact2ToViewProfileFragment(profInfo);
                        action.setInfo(profInfo);
                        action.setInfo(profInfo);
                        navController.navigate(action);
                    }

                    @Override
                    public void otherClicked(View v, int position) {
                        View val = linearLayoutManager.findViewByPosition(position);
                        assert val != null;
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
                });
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return root;



    }
}