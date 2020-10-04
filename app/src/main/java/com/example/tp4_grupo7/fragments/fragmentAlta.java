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

import com.example.tp4_grupo7.R;
import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class fragmentAlta extends Fragment {
    private View view;
    private EditText edtId, edtNombre, edtStock;
    private Spinner spCategoria;
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
        spCategoria = view.findViewById(R.id.sp_categoria);
        btnAgregar = view.findViewById(R.id.btn_agregar);
        cargarCategorias();
        btnAgregar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cargarArticulo();
            }
        });

        return view;
    }

    public void cargarCategorias(){
        //Traer categorias de la BD
        Categoria[] arraySpinner = new Categoria[] {new Categoria(1, "Prueba"), new Categoria(2,"Test")};
        ArrayAdapter<Categoria> adapterSpinner = new ArrayAdapter<Categoria>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        spCategoria.setAdapter(adapterSpinner);
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
        Categoria categoriaSelec = (Categoria) spCategoria.getSelectedItem();
        if(!nombre.trim().isEmpty() && stock > 0 && id > 0){
            //Chequear que el ID no exista en la BD
                //Toast.makeText(getActivity() ,"El ID ingresado corresponde a otro artículo",Toast.LENGTH_LONG).show();

            Articulo nuevo = new Articulo();
            nuevo.setId(id);
            nuevo.setNombre(nombre);
            nuevo.setStock(stock);
            nuevo.setCategoria(categoriaSelec);
            try{
                //Agregar articulo a BD
                //Toast.makeText(getActivity() ,"Artículo añadido!",Toast.LENGTH_SHORT).show();
                //edtNombre.setText("");
                //edtStock.setText("");
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(getActivity() ,"Error al añadir el artículo",Toast.LENGTH_SHORT).show();
            }finally {
                //Cerrar BD
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
