package com.example.jjherram.repasoexamen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterProductos extends RecyclerView.Adapter<AdapterProductos.PorductHolder> {

    private List<Producto> productos;
    private Context context;

    public AdapterProductos(List<Producto> productos, Context context) {
        this.productos = productos;
        this.context = context;
    }

    @NonNull
    @Override
    public PorductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.product_item,parent,false);
        return new PorductHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PorductHolder holder, int position) {
        Producto producto=productos.get(position);

        holder.dia.setText(producto.getDia()+"");
        holder.semana.setText(producto.getDiaSemana()+"");
        holder.precio.setText(producto.getPrecio()+"");

    }

    @Override
    public int getItemCount() {
        return productos.size();
    }

    public static class PorductHolder extends RecyclerView.ViewHolder{

        private TextView dia;
        private TextView semana;
        private TextView precio;
        private ImageView img;

        public PorductHolder(@NonNull View itemView) {
            super(itemView);
            dia=itemView.findViewById(R.id.dia);
            semana=itemView.findViewById(R.id.semana);
            precio=itemView.findViewById(R.id.precio);
            img=itemView.findViewById(R.id.imageView2);
        }
    }
}
