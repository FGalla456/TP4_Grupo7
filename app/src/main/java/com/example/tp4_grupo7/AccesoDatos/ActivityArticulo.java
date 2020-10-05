package com.example.tp4_grupo7.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.ListView;

import com.example.tp4_grupo7.Adapter.articuloAdapter;
import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import android.os.Bundle;
import android.widget.Toast;

public class ActivityArticulo extends AsyncTask<String, Void, String> {

    private static String result2;
    private static ArrayList<Articulo> listaProductos = new ArrayList<Articulo>();
    private Articulo art;
    private Context context;

    public ActivityArticulo(Articulo artic, Context ct)
    {
        art = artic;
        context = ct;
    }


    @Override
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            st.execute("INSERT INTO articulo (id, nombre, stock, idCategoria) VALUES ("+ art.getId() +", '"+art.getNombre()+"', "+art.getStock()+" ,"+art.getCategoria().getId()+")");
            result2 = " ";
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
        if (response.equals("Conexion exitosa"))
        {
            Toast.makeText(context ,"El artìculo se cargó exitosamente",Toast.LENGTH_LONG).show();
        }

    }
}