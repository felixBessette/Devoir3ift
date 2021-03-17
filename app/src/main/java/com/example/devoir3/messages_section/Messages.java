package com.example.devoir3.messages_section;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devoir3.ListAdapter;
import com.example.devoir3.R;

public class Messages extends Fragment {
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.liste, container, false);
        recyclerView = root.findViewById(R.id.favori_recycler);
        TextView title = root.findViewById(R.id.page_title);
        title.setText(R.string.rech_message);
        int ids = R.layout.row_messages;
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(getContext());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        ListAdapter myAdapter = new ListAdapter(inflater, ids,
                linearLayoutManager, navController);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return root;


    }
}