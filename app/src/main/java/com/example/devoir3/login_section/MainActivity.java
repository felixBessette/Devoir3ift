package com.example.devoir3.login_section;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.devoir3.NavigationMain;
import com.example.devoir3.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        Button b1 = findViewById(R.id.inscrire);
        Button b2 = findViewById(R.id.mdp_oublier);
        Button b3 = findViewById(R.id.retour);

        b1.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, NavigationMain.class);
            startActivity(intent1);
            finish();
        });

        b2.setOnClickListener(v -> {
            Intent intent2 = new Intent(this, MotPasseOublier.class);
            startActivity(intent2);
        });

        b3.setOnClickListener(v -> {
            Intent intent3 = new Intent(this, CreerCompte.class);
            startActivity(intent3);
        });
    }
}