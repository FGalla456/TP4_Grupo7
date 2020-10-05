package com.example.tp4_grupo7.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp4_grupo7.AccesoDatos.ActivityListarArticulos;
import com.example.tp4_grupo7.Adapter.articuloAdapter;
import com.example.tp4_grupo7.R;
import com.example.tp4_grupo7.domain.Articulo;

import java.security.AccessController;
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
        grid = view.findViewById(R.id.gv_articulos);
        ActivityListarArticulos task = new ActivityListarArticulos(grid, view.getContext());
        task.execute();
        return view;
    }
}