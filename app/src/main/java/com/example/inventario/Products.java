package com.example.inventario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Products extends AppCompatActivity {
    private EditText txtCode, txtName, txtDescription, txtSede, txtCantidad, txtValue;
    private FirebaseDatabase mydb = FirebaseDatabase.getInstance();
    private DatabaseReference ref = mydb.getReference("products");
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);

        txtCode = (EditText)findViewById(R.id.txtCode);
        txtName = (EditText)findViewById(R.id.txtName);
        txtDescription = (EditText)findViewById(R.id.txtDescription);
        txtSede = (EditText)findViewById(R.id.txtSede);
        txtCantidad = (EditText)findViewById(R.id.txtCant);
        txtValue = (EditText)findViewById(R.id.txtValue);



    }


    public void saveProduct(View view) {
        String code = txtCode.getText().toString();
        String name = txtName.getText().toString();
        String description = txtDescription.getText().toString();
        String sede = txtSede.getText().toString();
        Integer cantidad = Integer.parseInt(txtCantidad.getText().toString());
        Double valor = Double.parseDouble(txtValue.getText().toString());
        ref.child(name).setValue(new Product(code, name, description, sede, valor, cantidad));
    }

    public void goToViewProducts(View view) {
        intent = new Intent(Products.this,  List_Products.class);
        startActivity(intent);
    }
}
