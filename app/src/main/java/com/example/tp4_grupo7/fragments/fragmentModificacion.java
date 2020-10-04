package com.example.tp4_grupo7.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo7.R;
import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;

public class fragmentModificacion extends Fragment {
    private View view;
    private EditText edtId, edtNombre, edtStock;
    private Spinner spCategoria;
    private Button btnBuscar, btnModificar;
    private ArrayAdapter<Categoria> adapterSpinner;
    private Articulo articuloLocal;
    private String nombre;
    private Integer stock;

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
        cargarCategorias();
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

    public void cargarCategorias(){
        //Traer categorias de la BD
        Categoria[] arraySpinner = new Categoria[] {new Categoria(1, "Prueba"), new Categoria(2,"Test")};
        adapterSpinner = new ArrayAdapter<Categoria>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        spCategoria.setAdapter(adapterSpinner);
    }

    public void buscarArticulo(){
        if (!edtId.getText().toString().isEmpty()) {
            Integer id = Integer.parseInt(edtId.getText().toString());
            //Buscar en BD el ID
            //articuloLocal = ;
            //edtNombre.setText(articuloLocal.getNombre());
            //edtStock.setText(articuloLocal.getStock().toString());
            //Integer posicion = adapterSpinner.getPosition(articuloLocal.getCategoria());
            //spCategoria.setSelection(posicion);
            //Si no existe
            //Toast.makeText(getActivity() ,"Artículo con ID: "+ id +" no encontrado.",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "Debe ingresar un ID para buscar.", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificarArticulo(){
        if(articuloLocal.getId() != -1) {
            if (!edtStock.getText().toString().isEmpty()) {
                stock = Integer.parseInt(edtStock.getText().toString());
            } else {
                stock = -1;
            }
            nombre = edtNombre.getText().toString();
            Categoria categoriaSelec = (Categoria) spCategoria.getSelectedItem();
            if (!nombre.trim().isEmpty() && stock > 0) {
                articuloLocal.setNombre(nombre);
                articuloLocal.setStock(stock);
                articuloLocal.setCategoria(categoriaSelec);
                try {
                    //Modificar articulo en BD
                    //Toast.makeText(getActivity() ,"Artículo modificado!",Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "Error al modificar el artículo", Toast.LENGTH_SHORT).show();
                } finally {
                    //Cerrar BD
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
}