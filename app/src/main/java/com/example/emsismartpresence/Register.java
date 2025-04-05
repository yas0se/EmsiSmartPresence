package com.example.emsismartpresence;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private EditText etEmail, etPassword, etConfirmePassword;
    private Button btnRegister;
    private final String validEmail = "user@example.com";
    private final String validPassword = "123456";
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
// Récupération des éléments UI
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmePassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_Register);


// Gestion du clic sur le bouton
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confpassword = etConfirmePassword.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }
        if (confpassword.equals(password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, task -> {
                        if(task.isSuccessful()) {
                            Toast.makeText(this, "Connexion réussie!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(this, "Erreur: "+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }else {

            Toast.makeText(this, "mot de passe incorrect", Toast.LENGTH_SHORT).show();
        }

                /*      if (email.equals(validEmail) && password.equals(validPassword)) {
                        Toast.makeText(this, "Authentification réussie !", Toast.LENGTH_SHORT).show();
                } else {

                        Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }*/


    }

}