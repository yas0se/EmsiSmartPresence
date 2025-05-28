package com.example.emsismartpresence;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import androidx.cardview.widget.CardView;



public class MainActivity extends AppCompatActivity {

    TextView adminName;
    CardView card5;
    CardView card7;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        adminName = findViewById(R.id.dashboard_adminName);
        CardView card5 = findViewById(R.id.card5);
        CardView card7 = findViewById(R.id.card7);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            String displayName = user.getDisplayName();
            if (displayName != null && !displayName.isEmpty()) {
                adminName.setText("Mr/Mme " + displayName);
            } else {
                adminName.setText("Mr/Mme " + email);
            }
        }
        card5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Maps.class);
                startActivity(intent);
            }
        });

        card7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, assistant_virtuel.class);
                startActivity(intent);
            }
        });


    }
}