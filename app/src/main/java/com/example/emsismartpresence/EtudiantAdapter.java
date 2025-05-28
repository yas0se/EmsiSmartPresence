package com.example.emsismartpresence;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;

import java.util.List;

public class EtudiantAdapter extends ArrayAdapter<Etudiant> {
    private final Context context;
    private final List<Etudiant> etudiants;

    public EtudiantAdapter(Context context, List<Etudiant> etudiants) {
        super(context, 0, etudiants);
        this.context = context;
        this.etudiants = etudiants;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Etudiant etudiant = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_multiple_choice, parent, false);
        }

        CheckedTextView checkedTextView = (CheckedTextView) convertView;
        checkedTextView.setText(etudiant.getNom());
        checkedTextView.setChecked(!etudiant.isAbsent());

        // Toggle absence state on click
        checkedTextView.setOnClickListener(v -> {
            boolean isChecked = !checkedTextView.isChecked();
            checkedTextView.setChecked(isChecked);
            etudiant.setAbsent(!isChecked); // absent = !present
        });

        return convertView;
    }
}


