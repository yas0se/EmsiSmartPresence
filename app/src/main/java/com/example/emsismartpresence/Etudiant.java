package com.example.emsismartpresence;

public class Etudiant {
    private String id;
    private String nom;
    private boolean absent;

    public Etudiant() {}

    public Etudiant(String id, String nom, boolean absent) {
        this.id = id;
        this.nom = nom;
        this.absent = absent;
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public boolean isAbsent() { return absent; }

    public void setId(String id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setAbsent(boolean absent) { this.absent = absent; }
}


