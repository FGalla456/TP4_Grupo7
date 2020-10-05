package com.example.tp4_grupo7.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;
import com.example.tp4_grupo7.fragments.fragmentAlta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ActivityModificarArticulo extends AsyncTask<String, Void, String> {
    private Articulo articulo;
    private Context context;


    private static String result2;

    public ActivityModificarArticulo(Articulo art, Context ct)
    {
        articulo = art;
        context = ct;


    }

    @Override
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE articulo SET nombre = '" + articulo.getNombre() + "', stock = " + articulo.getStock() + ", idCategoria = " + articulo.getCategoria().getId() + " WHERE id = "+ articulo.getId());
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
        if (response.equals("Conexion exitosa")){
            Toast.makeText(context ,"El artículo se modificó exitosamente",Toast.LENGTH_LONG).show();
        }
    }
}
