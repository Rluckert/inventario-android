package com.example.inventario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword;
    private ProgressDialog progressDialog;
    private Resources resources;
    private Intent intent;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();

        txtEmail = (EditText)findViewById(R.id.txtEmailRg);
        txtPassword = (EditText)findViewById(R.id.txtPasswordRg);



        progressDialog = new ProgressDialog(this);

        resources = this.getResources();



    }

    public void registrarUsuario(View view){
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Se debe ingresar una clave", Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Registro.this, "Se ha registrado correctamente", Toast.LENGTH_LONG).show();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                        } else {
                            Log.w("Error", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registro.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();

                        }

                        progressDialog.dismiss();
                    }
                });

    }

    public void goToLogin(View view){
        intent = new Intent(Registro.this, MainActivity.class);
        startActivity(intent);
    }


}
