package com.example.tp4.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.tp4.AccesoDatos.ActivityListarArticulos;
import com.example.tp4.Adapter.articuloAdapter;
import com.example.tp4.R;
import com.example.tp4.domain.Articulo;

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