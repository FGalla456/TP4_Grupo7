package com.example.tp4_grupo7.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
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
import java.util.List;

import android.os.Bundle;
import android.widget.Spinner;

public class ActivityListarCategorias extends AsyncTask<String, Void, String> {

    private ArrayAdapter<Categoria> adapterSpinner;
    private Spinner spCategorias;
    private Context context;

    private static String result2;
    private static ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();


    public ActivityListarCategorias(Spinner sp, Context ct)
    {
        this.spCategorias = sp;
        context = ct;
    }

    @Override
    protected String doInBackground(String... urls) {
        String response = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM categoria");
            result2 = " ";


            Categoria categ;

            while(rs.next()) {

                categ = new Categoria();

                categ.setId(rs.getInt("id"));
                categ.setDescripcion(rs.getString("descripcion"));

                listaCategorias.add(categ);
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

        adapterSpinner = new ArrayAdapter<Categoria>(context, android.R.layout.simple_spinner_item, listaCategorias);
        spCategorias.setAdapter(adapterSpinner);

    }
}