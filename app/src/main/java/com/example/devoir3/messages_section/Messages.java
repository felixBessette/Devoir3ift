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
        View root = inflater.inflate(R.layout.messages, container, false);
        recyclerView = root.findViewById(R.id.recycler_message);
        int[] ids = new int[1];
        ids[0] = R.layout.row_messages;
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
                        MessagesDirections.ActionNavigationMessagesToConversation action =
                                MessagesDirections.actionNavigationMessagesToConversation(profInfo);
                        action.setProfileInfo(profInfo);
                        navController.navigate(action);
                    }

                    @Override
                    public void otherClicked(View v, int position) {

                    }
                }, linearLayoutManager, navController);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return root;


    }
}