package com.example.emsismartpresence;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class Rattrapages extends AppCompatActivity {

    private ListView listRattrapages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rattrapages);

        listRattrapages = findViewById(R.id.list_rattrapages);

        List<String> rattrapages = Arrays.asList(
                "Lundi 3 Juin - SC32 - 10:00 à 12:00",
                "Jeudi 6 Juin - LI1 - 14:00 à 16:00",
                "Vendredi 7 Juin - SC33 - 08:30 à 10:00"
        );

        listRattrapages.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rattrapages));
    }
}
