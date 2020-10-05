package com.example.tp4_grupo7.fragments;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp4_grupo7.AccesoDatos.ActivityArticulo;
import com.example.tp4_grupo7.AccesoDatos.ActivityListarCategorias;
import com.example.tp4_grupo7.R;
import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class fragmentAlta extends Fragment {
    private View view;
    private EditText edtId, edtNombre, edtStock;
    private Spinner spCategoriaAlta;
    private Button btnAgregar;
    private Integer id, stock;
    private String nombre, categoria;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_alta,container,false);
        edtId = view.findViewById(R.id.edt_id);
        edtNombre = view.findViewById(R.id.edt_nombre);
        edtStock = view.findViewById(R.id.edt_stock);
        spCategoriaAlta = view.findViewById(R.id.sp_categoriaAlta);
        btnAgregar = view.findViewById(R.id.btn_agregar);
        ActivityListarCategorias task = new ActivityListarCategorias(spCategoriaAlta, view.getContext());
        task.execute();
        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cargarArticulo();
            }
        });

        return view;
    }



    public void cargarArticulo(){
        if(!edtId.getText().toString().isEmpty()){
            id = Integer.parseInt(edtId.getText().toString());
        }else{
            id = -1;
        }
        if(!edtStock.getText().toString().isEmpty()) {
            stock = Integer.parseInt(edtStock.getText().toString());
        }else{
            stock = -1;
        }
        nombre = edtNombre.getText().toString();
        Categoria categoriaSelec = (Categoria) spCategoriaAlta.getSelectedItem();
        if(!nombre.trim().isEmpty() && stock > 0 && id > 0){
            //Chequear que el ID no exista en la BD
                //Toast.makeText(getActivity() ,"El ID ingresado corresponde a otro artículo",Toast.LENGTH_LONG).show();

            Articulo nuevo = new Articulo();
            nuevo.setId(id);
            nuevo.setNombre(nombre);
            nuevo.setStock(stock);
            nuevo.setCategoria(categoriaSelec);
            try{
                ActivityArticulo task2 = new ActivityArticulo(nuevo, view.getContext());
                task2.execute();
                edtNombre.setText("");
                edtStock.setText("");
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity() ,"Error al añadir el artículo",Toast.LENGTH_SHORT).show();
            }
        }else if(id < 0){
            Toast.makeText(getActivity() ,"Debe colocar un ID de artículo.",Toast.LENGTH_SHORT).show();
        }else if(nombre.trim().isEmpty()){
            Toast.makeText(getActivity() ,"Debe colocar un nombre para el artículo.",Toast.LENGTH_SHORT).show();
        }else if(stock < 0){
            Toast.makeText(getActivity() ,"El stock debe ser mayor a cero.",Toast.LENGTH_SHORT).show();
        }
    }
}
