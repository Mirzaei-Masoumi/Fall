package com.fall.masomi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnFall;
    Button btnPoets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFall = findViewById(R.id.btnFall);
        btnPoets = findViewById(R.id.btnPoets);

        btnFall.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FalIntroActivity.class);
            startActivity(intent);
        });

        btnPoets.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PoetsActivity.class);
            startActivity(intent);
        });
    }
}