package com.example.inventario;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class List_Products extends AppCompatActivity {
    RecyclerView nRecyclerView;
    List<Product> productos;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_products);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle("Objetos del inventario");

        nRecyclerView = findViewById(R.id.myRecycler);
        nRecyclerView.setHasFixedSize(true);

        nRecyclerView.setLayoutManager(new LinearLayoutManager(this));

         productos = new ArrayList<>();

         FirebaseDatabase database = FirebaseDatabase.getInstance();

         adapter = new Adapter(productos);
         nRecyclerView.setAdapter(adapter);
         database.getReference("products").addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 productos.removeAll(productos);
               for(DataSnapshot snapshot :
               dataSnapshot.getChildren()){
                   Product producto = snapshot.getValue(Product.class);
                   productos.add(producto);
               }
               adapter.notifyDataSetChanged();
             }

             @Override
             public void onCancelled( DatabaseError databaseError) {

             }
         });


    }
}
