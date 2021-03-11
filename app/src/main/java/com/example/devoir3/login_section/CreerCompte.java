package com.example.devoir3.login_section;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.devoir3.NavigationMain;
import com.example.devoir3.R;

public class CreerCompte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creer_compte);

        Button b1 = findViewById(R.id.inscrire);
        Button b2 = findViewById(R.id.retour);

        b1.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, NavigationMain.class);
            startActivity(intent1);
            finish();
        });

        b2.setOnClickListener(v -> {
            finish();
        });
    }
}