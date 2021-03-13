package com.example.devoir3.recherche_section;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ResultRecherche#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ResultRecherche extends Fragment {
    String[] studentName, studentInterest;
    int img = R.drawable.avatar;
    RecyclerView recyclerView;
    TextView criteria;

    public ResultRecherche() {
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
        View root = inflater.inflate(R.layout.search_result, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        studentName = getResources().getStringArray(R.array.student_name);
        criteria = root.findViewById(R.id.search_by);
        if (getArguments() != null) {
            ResultRechercheArgs args = ResultRechercheArgs.fromBundle(getArguments());
            String message = args.getMessage();
            if (message.equals("interet")) {
                criteria.setText(R.string.interet);
                studentInterest = getResources().getStringArray(R.array.student_interet);
            }
            else if (message.equals("cours")) {
                criteria.setText(R.string.cours);
                studentInterest = getResources().getStringArray(R.array.student_cours);
            }
            else {
                criteria.setText(R.string.club);
                studentInterest = getResources().getStringArray(R.array.student_clubs);
            }
        }
        int ids = R.layout.row_result;
        LinearLayoutManager linearLayoutManager = new  LinearLayoutManager(getContext());
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        ListAdapter myAdapter = new ListAdapter(inflater, ids, linearLayoutManager, navController);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(root.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return root;


    }
}