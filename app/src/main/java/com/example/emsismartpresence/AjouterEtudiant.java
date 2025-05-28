package com.example.emsismartpresence;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AjouterEtudiant extends AppCompatActivity {

    private EditText etNom;
    private Spinner spSite, spGroupe, spFiliere;
    private Button btnAjouter;

    private DatabaseReference databaseEtudiants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_etudiant);

        etNom = findViewById(R.id.et_nom);
        spFiliere = findViewById(R.id.sp_filiere);
        spSite = findViewById(R.id.sp_site);
        spGroupe = findViewById(R.id.sp_groupe);
        btnAjouter = findViewById(R.id.btn_ajouter);

        databaseEtudiants = FirebaseDatabase.getInstance(
                "https://emsismartpresence-43c1a-default-rtdb.europe-west1.firebasedatabase.app"
        ).getReference("etudiants");

        String[] filieres = {"4IIR", "3IIR", "5IIR"};
        String[] sites = {"Centre", "Maarif"};
        String[] groupes = {"G 01", "G 02", "G 03"};

        spFiliere.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, filieres));
        spSite.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, sites));
        spGroupe.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, groupes));

        Log.i("AjouterEtudiant", "Bouton prêt, écouteur en cours d'activation");

        btnAjouter.setOnClickListener(v -> ajouterEtudiant());
    }

    private void ajouterEtudiant() {
        String nom = etNom.getText().toString().trim();
        String filiere = spFiliere.getSelectedItem().toString();
        String site = spSite.getSelectedItem().toString();
        String groupe = spGroupe.getSelectedItem().toString();

        Log.i("AjouterEtudiant", "Nom saisi: " + nom);
        Log.i("AjouterEtudiant", "Filière: " + filiere + ", Site: " + site + ", Groupe: " + groupe);

        if (!nom.isEmpty()) {
            String id = databaseEtudiants.push().getKey();
            String path = filiere + "/" + site + "_" + groupe;

            Log.i("AjouterEtudiant", "ID généré: " + id);
            Log.i("AjouterEtudiant", "Chemin Firebase: etudiants/" + path + "/" + id);

            Etudiant etudiant = new Etudiant(id, nom, false);

            databaseEtudiants.child(path).child(id).setValue(etudiant)
                    .addOnSuccessListener(aVoid -> {
                        Log.i("AjouterEtudiant", "Succès: étudiant ajouté à Firebase");
                        Toast.makeText(this, "Étudiant ajouté !", Toast.LENGTH_SHORT).show();
                        etNom.setText("");
                    })
                    .addOnFailureListener(e -> {
                        Log.e("AjouterEtudiant", "Erreur lors de l’ajout: " + e.getMessage());
                        Toast.makeText(this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show();
                    });
        } else {
            Log.w("AjouterEtudiant", "Nom vide : saisie requise");
            Toast.makeText(this, "Nom requis", Toast.LENGTH_SHORT).show();
        }
    }

}

