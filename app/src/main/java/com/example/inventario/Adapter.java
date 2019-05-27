package com.example.inventario;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ProductsViewHolder>{

    List<Product> products;

    public Adapter(List<Product> productos){
        this.products = productos;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        ProductsViewHolder holder = new ProductsViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Product producto = products.get(position);
        holder.txtNameCard.setText("Nombre del artículo: "+producto.getNombre());
        holder.txtCodigoCard.setText("Código: "+producto.getId());
        holder.txtDescription.setText("Descripcion: "+producto.getDescripcion());
        holder.txtSedeCard.setText("Nombre del artículo"+producto.getSede());
        holder.txtValueCard.setText("Valor: "+producto.getPrecio());
        holder.txtCantidadCard.setText("Cantidad: "+producto.getCantidad());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ProductsViewHolder extends RecyclerView.ViewHolder{
        TextView txtNameCard, txtCodigoCard, txtDescription, txtSedeCard, txtValueCard, txtCantidadCard;
        public ProductsViewHolder(View itemView){
            super(itemView);
            txtNameCard = (TextView)itemView.findViewById(R.id.nameCard);
            txtCodigoCard = (TextView)itemView.findViewById(R.id.codigoCard);
            txtDescription = (TextView)itemView.findViewById(R.id.descriptionCard);
            txtSedeCard = (TextView)itemView.findViewById(R.id.sedeCard);
            txtValueCard = (TextView)itemView.findViewById(R.id.valorCard);
            txtCantidadCard = (TextView)itemView.findViewById(R.id.cantidadCard);
        }
    }


}
