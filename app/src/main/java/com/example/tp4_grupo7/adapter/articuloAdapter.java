package com.example.tp4_grupo7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.tp4_grupo7.R;
import com.example.tp4_grupo7.domain.Articulo;

import java.util.ArrayList;

public class articuloAdapter extends BaseAdapter {

    private ArrayList<Articulo> articulos;
    private Context context;

    public articuloAdapter(Context context, ArrayList<Articulo> articulos){
        this.context = context;
        this.articulos = articulos;
    }

    @Override
    public int getCount() {
        return articulos.size();
    }

    @Override
    public Articulo getItem(int i) {
        return articulos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = convertView;
        if (convertView == null){
            view = inflater.inflate(R.layout.card_template, null);
        }

        TextView nombre = view.findViewById(R.id.txt_nombre);
        TextView stock = view.findViewById(R.id.txt_stock);
        TextView categoria = view.findViewById(R.id.txt_categoria);
        nombre.setText(getItem(i).getId() +" - " + getItem(i).getNombre());
        stock.setText("Stock: " + getItem(i).getStock().toString());
        categoria.setText("Categoria: " + getItem(i).getCategoria().getDescripcion());

        return view;
    }
}
