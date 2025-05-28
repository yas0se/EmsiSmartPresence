package com.example.emsismartpresence;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class Documents extends AppCompatActivity {

    private ListView listDocs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documents);

        listDocs = findViewById(R.id.list_documents);

        List<String> documents = Arrays.asList(
                "État d'enseignement - Semestre 1",
                "État d'enseignement - Semestre 2",
                "Attestation de présence",
                "Fiche de paie - Avril 2025"
        );

        listDocs.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, documents));
    }
}
