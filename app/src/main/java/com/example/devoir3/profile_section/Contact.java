package com.example.devoir3.profile_section;

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

import com.example.devoir3.ListAdapter;
import com.example.devoir3.R;


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
        int ids = R.layout.row_contacts;
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(getContext());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        ListAdapter myAdapter = new ListAdapter(inflater, ids,
                linearLayoutManager , navController);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return root;



    }
}