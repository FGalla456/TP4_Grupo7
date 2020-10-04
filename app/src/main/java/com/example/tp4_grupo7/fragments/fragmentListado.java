package com.example.tp4_grupo7.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp4_grupo7.R;
import com.example.tp4_grupo7.adapter.articuloAdapter;
import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;

import java.util.ArrayList;

public class fragmentListado extends Fragment {

    private View view;
    private GridView grid;
    private articuloAdapter adapter;
    private ArrayList<Articulo> lista;
    private Articulo selected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_listado, container, false);

        adapter = new articuloAdapter(view.getContext(), cargarArticulos());
        grid = view.findViewById(R.id.gv_articulos);
        grid.setAdapter(adapter);

        return view;
    }

    public ArrayList<Articulo> cargarArticulos(){
        lista = new ArrayList<>();
        //Traer articulos de BD
        //lista.add();
        lista.add(new Articulo(1, "test", 0, new Categoria(1, "categoria"))); //Eliminar una vez que tengamos la BD
        return lista;
    }
}