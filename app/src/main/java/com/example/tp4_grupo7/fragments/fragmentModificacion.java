package com.example.tp4_grupo7.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
            Integer id = Integer.parseInt(edtId.getText().toString());
            ActivityBuscador task2 = new ActivityBuscador(articuloLocal, id, view.getContext()); /*

            {
                @Override
                protected void onPostExecute(String response) {
                super.onPostExecute(response);
                    if (articuloLocal.getId() != -1)
                    {
                        edtNombre.setText(articuloLocal.getNombre());
                        edtStock.setText(articuloLocal.getStock().toString());
                        Integer posicion = adapterSpinner.getPosition(articuloLocal.getCategoria());
                        spCategoria.setSelection(posicion);
                        //Si no existe
                        //Toast.makeText(getActivity() ,"Artículo con ID: "+ id +" no encontrado.",Toast.LENGTH_SHORT).show();
                    }
            }
            };*/

            task2.execute();
            Toast.makeText(getActivity() ,"Artículo con ID: "+ articuloLocal.getId().toString() +"",Toast.LENGTH_SHORT).show();
            //Buscar en BD el ID
            //testBuscarArt(id);


        } else {
            Toast.makeText(getActivity(), "Debe ingresar un ID para buscar.", Toast.LENGTH_SHORT).show();
        }
    }
/*
    public void testBuscarArt(int idModificar)
    {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo a INNER JOIN categoria as c on c.id = a.idCategoria WHERE a.id = " +idModificar);


            Articulo prod;
            Categoria categ;

            while(rs.next()) {
                prod = new Articulo();
                categ = new Categoria();
                prod.setId(rs.getInt("a.id"));
                categ.setId(rs.getInt("c.id"));
                categ.setDescripcion(rs.getString("c.descripcion"));
                prod.setCategoria(categ);
                prod.setNombre(rs.getString("a.nombre"));
                prod.setStock(rs.getInt("a.stock"));
                articuloLocal = prod;
            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
*/
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