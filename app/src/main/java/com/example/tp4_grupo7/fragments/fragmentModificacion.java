package com.example.tp4_grupo7.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo7.AccesoDatos.ActivityBuscador;
import com.example.tp4_grupo7.AccesoDatos.ActivityListarArticulos;
import com.example.tp4_grupo7.AccesoDatos.ActivityListarCategorias;
import com.example.tp4_grupo7.AccesoDatos.ActivityModificarArticulo;
import com.example.tp4_grupo7.AccesoDatos.DataDB;
import com.example.tp4_grupo7.R;
import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class fragmentModificacion extends Fragment {
    private View view;
    private EditText edtId, edtNombre, edtStock;
    private Spinner spCategoria;
    private Button btnBuscar, btnModificar;
    private Articulo articuloLocal;
    private String nombre;
    private Integer stock, id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_modificacion,container,false);
        edtId = view.findViewById(R.id.edt_id);
        edtNombre = view.findViewById(R.id.edt_nombre);
        edtStock = view.findViewById(R.id.edt_stock);
        spCategoria = view.findViewById(R.id.sp_categoria);
        btnBuscar = view.findViewById(R.id.btn_buscar);
        btnModificar = view.findViewById(R.id.btn_modificar);
        articuloLocal = new Articulo();
        ActivityListarCategorias task = new ActivityListarCategorias(spCategoria, view.getContext());
        task.execute();

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarArticulo();
            }
        });

        btnModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarArticulo();
            }
        });
        return view;
    }

    public void buscarArticulo(){
        if (!edtId.getText().toString().isEmpty()) {
            id = Integer.parseInt(edtId.getText().toString());
            ActivityBuscador task2 = new ActivityBuscador(articuloLocal, id, view.getContext(), edtNombre, edtStock, spCategoria);
            task2.execute();

        } else {
            Toast.makeText(getActivity(), "Debe ingresar un ID para buscar.", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificarArticulo(){
        if(id != -1) {
            if (!edtStock.getText().toString().isEmpty()) {
                stock = Integer.parseInt(edtStock.getText().toString());
            } else {
                stock = -1;
            }
            nombre = edtNombre.getText().toString();
            Categoria categoriaSelec = (Categoria) spCategoria.getSelectedItem();
            if (!nombre.trim().isEmpty() && stock > 0) {
                articuloLocal.setId(id);
                articuloLocal.setNombre(nombre);
                articuloLocal.setStock(stock);
                articuloLocal.setCategoria(categoriaSelec);
                try {
                    ActivityModificarArticulo task3 = new ActivityModificarArticulo(articuloLocal, view.getContext());
                    task3.execute();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error al modificar el artículo", Toast.LENGTH_SHORT).show();
                }
            } else if (nombre.trim().isEmpty()) {
                Toast.makeText(getActivity(), "Debe colocar un nombre para el artículo.", Toast.LENGTH_SHORT).show();
            } else if (stock < 0) {
                Toast.makeText(getActivity(), "El stock debe ser mayor a cero.", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "Debe buscar un artículo para modificar.", Toast.LENGTH_SHORT).show();
        }
    }

    /*private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }*/
}