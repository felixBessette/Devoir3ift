package com.example.devoir3.notif_section;

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
        View root = inflater.inflate(R.layout.liste, container, false);
        recyclerView = root.findViewById(R.id.favori_recycler);
        TextView title = root.findViewById(R.id.page_title);
        title.setText(R.string.notif);
        studentName = getResources().getStringArray(R.array.student_name);
        studentInterest = getResources().getStringArray(R.array.student_interet);
        int ids = R.layout.row_notif;
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