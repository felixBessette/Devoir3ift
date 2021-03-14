package com.example.devoir3;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class NavigationMain extends AppCompatActivity {
    public static ArrayList<StudentList.Student> studentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation);

        StudentList s = new StudentList(getApplicationContext());
        studentList = s.initialiseStudentList();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = androidx.navigation.Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            if (navController.getCurrentDestination() != null) {
                navController.popBackStack();
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.navigation_messages ||
                        destination.getId() == R.id.navigation_notif ||
                        destination.getId() == R.id.navigation_recherche ||
                        destination.getId() == R.id.navigation_profil ||
                        destination.getId() == R.id.navigation_saved) {
                    back.setVisibility(View.INVISIBLE);
                }
                else {
                    back.setVisibility(View.VISIBLE);
                }
            }
        });
        /*
        Button b1 = findViewById(R.id.interet);
        b1.setOnClickListener(v -> {
            Intent intent = new Intent(this, ResultActivity.class);
            startActivity(intent);
        });*/
    }


}