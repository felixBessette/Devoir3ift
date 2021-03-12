package com.example.devoir3.messages_section;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devoir3.ListAdapter;
import com.example.devoir3.R;
import com.example.devoir3.recherche_section.ResultRecherche;

public class Conversation extends Fragment {
    RecyclerView recyclerView;

    public Conversation() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ResultRecherche newInstance() {
        return new ResultRecherche();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.conversation, container, false);
        recyclerView = root.findViewById(R.id.convo_recycler);
        TextView name = root.findViewById(R.id.convo_name);
        ImageView picture = root.findViewById(R.id.convo_img);
        if (getArguments() != null) {
            ConversationArgs args = ConversationArgs.fromBundle(getArguments());
            String[] info = args.getProfileInfo();
            name.setText(info[0]);
            picture.setImageResource(Integer.parseInt(info[2]));
        }
        int[] ids = new int[1];
        ids[0] = R.layout.row_convo;
        ListAdapter myAdapter = new ListAdapter(inflater, ids,
                new ListAdapter.MyAdapterListener() {
            @Override
            public void profileClicked(View v, int info) {
            }

            @Override
            public void otherClicked(View v, int position) {

            }
        });
        recyclerView.setAdapter(myAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        return root;


    }
}