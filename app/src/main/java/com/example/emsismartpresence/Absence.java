package com.example.emsismartpresence;
/*
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Absence extends AppCompatActivity {

    private Spinner spFiliere, spSite, spGroupe;
    private EditText etDate, etRemarque;
    private ListView listViewEtudiants;
    private Button btnValider;

    private List<Etudiant> etudiantList = new ArrayList<>();
    private EtudiantAdapter adapter;

    private final String DB_URL = "https://emsismartpresence-43c1a-default-rtdb.europe-west1.firebasedatabase.app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);

        spFiliere = findViewById(R.id.spinner_filiere);
        spSite = findViewById(R.id.spinner_site);
        spGroupe = findViewById(R.id.spinner_groupe);
        etDate = findViewById(R.id.edit_date);
        etRemarque = findViewById(R.id.edit_remarque);
        listViewEtudiants = findViewById(R.id.list_etudiants);
        btnValider = findViewById(R.id.btn_valider);

        String[] filieres = {"4IIR", "3IIR", "5IIR"};
        String[] sites = {"Centre", "Site A", "Site B"};
        String[] groupes = {"G 01", "G 02", "G 03"};

        spFiliere.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, filieres));
        spSite.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sites));
        spGroupe.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, groupes));

        etDate.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                chargerEtudiantsAvecAbsences();
            }
        });

        btnValider.setOnClickListener(v -> enregistrerAbsences());
    }

    private void chargerEtudiantsAvecAbsences() {
        String filiere = spFiliere.getSelectedItem().toString();
        String site = spSite.getSelectedItem().toString();
        String groupe = spGroupe.getSelectedItem().toString();
        String dateStr = etDate.getText().toString().trim();

        if (dateStr.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une date", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] dateParts = dateStr.split("/");
        if (dateParts.length != 3) {
            Toast.makeText(this, "Format de date invalide (JJ/MM/AAAA)", Toast.LENGTH_SHORT).show();
            return;
        }

        String jour = dateParts[0];
        String mois = dateParts[1];
        String annee = dateParts[2];

        String pathEtudiants = filiere + "/" + site + "_" + groupe;
        String pathAbsences = filiere + "/" + site + "_" + groupe + "/" + jour + "/" + mois + "/" + annee;

        DatabaseReference refEtudiants = FirebaseDatabase.getInstance(DB_URL)
                .getReference("etudiants").child(pathEtudiants);

        DatabaseReference refAbsents = FirebaseDatabase.getInstance(DB_URL)
                .getReference("absences").child(pathAbsences);

        refAbsents.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot absSnapshot) {
                Set<String> absentIds = new HashSet<>();
                for (DataSnapshot ds : absSnapshot.getChildren()) {
                    Boolean isAbsent = ds.child("absent").getValue(Boolean.class);
                    if (Boolean.TRUE.equals(isAbsent)) {
                        absentIds.add(ds.getKey());
                    }
                }

                refEtudiants.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot etSnap) {
                        etudiantList.clear();
                        for (DataSnapshot ds : etSnap.getChildren()) {
                            Etudiant e = ds.getValue(Etudiant.class);
                            if (e != null) {
                                e.setAbsent(absentIds.contains(e.getId()));
                                etudiantList.add(e);
                            }
                        }

                        adapter = new EtudiantAdapter(Absence.this, etudiantList);
                        listViewEtudiants.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Absence.this, "Erreur chargement étudiants", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Absence.this, "Erreur chargement absences", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void enregistrerAbsences() {
        String filiere = spFiliere.getSelectedItem().toString();
        String site = spSite.getSelectedItem().toString();
        String groupe = spGroupe.getSelectedItem().toString();
        String dateStr = etDate.getText().toString().trim();
        String remarque = etRemarque.getText().toString().trim();

        if (dateStr.isEmpty()) {
            Toast.makeText(this, "Veuillez entrer une date", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] dateParts = dateStr.split("/");
        if (dateParts.length != 3) {
            Toast.makeText(this, "Format de date invalide (JJ/MM/AAAA)", Toast.LENGTH_SHORT).show();
            return;
        }

        String jour = dateParts[0];
        String mois = dateParts[1];
        String annee = dateParts[2];

        String path = "absences/" + filiere + "/" + site + "_" + groupe + "/" + jour + "/" + mois + "/" + annee;
        DatabaseReference ref = FirebaseDatabase.getInstance(DB_URL).getReference(path);

        for (Etudiant e : etudiantList) {
            if (e.isAbsent()) {
                ref.child(e.getId()).setValue(e);
            }
        }

        if (!remarque.isEmpty()) {
            ref.child("remarque").setValue(remarque);
        }

        Toast.makeText(this, "Absences enregistrées !", Toast.LENGTH_SHORT).show();
    }
}
*/

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Absence extends AppCompatActivity {

    private Spinner spFiliere, spSite, spGroupe;
    private EditText etDate, etRemarque;
    private ListView listViewEtudiants;
    private Button btnValider;

    private List<Etudiant> etudiantList;
    private EtudiantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absence);

        spFiliere = findViewById(R.id.spinner_filiere);
        spSite = findViewById(R.id.spinner_site);
        spGroupe = findViewById(R.id.spinner_groupe);
        etDate = findViewById(R.id.edit_date);
        etRemarque = findViewById(R.id.edit_remarque);
        listViewEtudiants = findViewById(R.id.list_etudiants);
        btnValider = findViewById(R.id.btn_valider);

        // Simuler données pour les spinners
        spFiliere.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"4IIR", "3IIR", "5IIR"}));
        spSite.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"Centre", "Site A", "Site B"}));
        spGroupe.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, new String[]{"G 01", "G 02", "G 03"}));

        // Simuler une liste fixe d’étudiants
        etudiantList = new ArrayList<>();
        etudiantList.add(new Etudiant("1", "Etudiant C1", false));
        etudiantList.add(new Etudiant("2", "Etudiant C2", false));
        etudiantList.add(new Etudiant("3", "Etudiant C3", false));
        etudiantList.add(new Etudiant("4", "Etudiant C4", false));
        etudiantList.add(new Etudiant("5", "Etudiant C5", false));
        etudiantList.add(new Etudiant("6", "Etudiant C6", false));
        etudiantList.add(new Etudiant("7", "Etudiant C7", false));
        etudiantList.add(new Etudiant("8", "Etudiant C8", false));
        etudiantList.add(new Etudiant("9", "Etudiant C9", false));

        // Affichage avec adapter custom
        adapter = new EtudiantAdapter(this, etudiantList);
        listViewEtudiants.setAdapter(adapter);

        btnValider.setOnClickListener(v -> {
            StringBuilder result = new StringBuilder("Absents :\n");
            for (Etudiant e : etudiantList) {
                if (e.isAbsent()) {
                    result.append("- ").append(e.getNom()).append("\n");
                }
            }
            Toast.makeText(this, result.toString(), Toast.LENGTH_LONG).show();
        });
    }
}
