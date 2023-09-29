package com.example.tp4_grupo7.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import android.os.Bundle;

public class ActivityBuscador extends AsyncTask<String, Void, String> {

    private Articulo articulo;
    private EditText nombre, stock;
    private Spinner spinner;
    private Articulo prod;
    private Categoria categ;
    private int idModificar;
    private Context context;


    private static String result2;

    public ActivityBuscador(Articulo art, int id, Context ct, EditText nombre, EditText stock, Spinner spinner)
    {
        articulo = art;
        idModificar = id;
        context = ct;
        this.nombre = nombre;
        this.stock = stock;
        this.spinner = spinner;
    }

    @Override
    protected String doInBackground(String... urls) {
        String response = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo a INNER JOIN categoria as c on c.id = a.idCategoria WHERE a.id = " +idModificar);

            result2 = " ";

            while(rs.next()) {
                prod = new Articulo();
                categ = new Categoria();
                prod.setId(rs.getInt("a.id"));
                categ.setId(rs.getInt("c.id"));
                categ.setDescripcion(rs.getString("c.descripcion"));
                prod.setCategoria(categ);
                prod.setNombre(rs.getString("a.nombre"));
                prod.setStock(rs.getInt("a.stock"));

            }
            response = "Conexion exitosa";
        }
        catch(Exception e) {
            e.printStackTrace();
            result2 = "Conexion no exitosa";
        }
        return response;
    }

    @Override
    protected void onPostExecute(String response) {
        if (response.equals("Conexion exitosa")){
            articulo = prod;
            if(articulo != null){
                nombre.setText(articulo.getNombre());
                nombre.setText(articulo.getNombre());
                stock.setText(articulo.getStock().toString());
                spinner.setSelection(articulo.getCategoria().getId()-1);
            }else{
                Toast.makeText(context ,"Artículo con ID: "+ idModificar +" no encontrado.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}