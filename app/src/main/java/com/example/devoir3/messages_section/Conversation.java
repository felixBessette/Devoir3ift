package com.example.devoir3.messages_section;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.devoir3.ListAdapter;
import com.example.devoir3.NavigationMain;
import com.example.devoir3.R;
import com.example.devoir3.StudentList;
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        if (getArguments() != null) {
            ConversationArgs args = ConversationArgs.fromBundle(getArguments());
            String[] info = args.getProfileInfo();
            StudentList.Student student = NavigationMain.studentList.get(Integer.parseInt(info[0]));
            name.setText(student.getName());
            picture.setImageResource(student.getPic());
        }
        int ids  = R.layout.row_convo;
        ListAdapter myAdapter = new ListAdapter(inflater, ids,
                linearLayoutManager ,navController);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(false);
        return root;
    }
}