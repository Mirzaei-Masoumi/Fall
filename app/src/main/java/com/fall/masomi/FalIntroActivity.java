package com.fall.masomi;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FalIntroActivity extends AppCompatActivity {

    Button btnFall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_fal);
        btnFall = findViewById(R.id.btnFall);
        btnFall.setOnClickListener(v -> {
//                Intent intent = new Intent(FalIntroActivity.this,FallIntroActivity.class);
        });
    }
}