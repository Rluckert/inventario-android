package com.example.inventario;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private EditText txtEmail;
    private EditText txtPassword;
    private Button btnRegistrar;
    private ProgressDialog progressDialog;
    private Resources resources;
    private Intent intent;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        resources = this.getResources();



    }

    public void login(View view){
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        Log.d("email", email);
        Log.d("email", password);

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Se debe ingresar una clave", Toast.LENGTH_LONG).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            intent = new Intent(MainActivity.this,  List_Products.class);
                            startActivity(intent);

                        } else {
                            Log.w("Error", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Sus datos son incorrectos", Toast.LENGTH_LONG).show();

                        }
    }
});

    }

    public void goToRegister(View view){
        intent = new Intent(MainActivity.this, Registro.class);
        startActivity(intent);

    }
}
