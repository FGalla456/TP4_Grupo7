package com.example.tp4_grupo7.AccesoDatos;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;

import com.example.tp4_grupo7.domain.Articulo;
import com.example.tp4_grupo7.domain.Categoria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ActivityModificarArticulo extends AsyncTask<String, Void, String> {
    private GridView lvProductos;
    private Context context;
    private int idProducto;

    private static String result2;
    private static ArrayList<Articulo> listaProductos = new ArrayList<Articulo>();

    public ActivityModificarArticulo(GridView lv, Context ct, Integer id)
    {
        lvProductos = lv;
        context = ct;
        idProducto = id;

    }

    @Override
    protected String doInBackground(String... urls) {
        String response = "";
        listaProductos.removeAll(listaProductos);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT a.id, nombre, stock,c.id,c.descripcion  FROM articulo a INNER JOIN categoria as c on a.idCategoria = c.id WHERE a.id = " + idProducto);
            result2 = " ";

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
                listaProductos.add(prod);
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
        com.example.tp4_grupo7.Adapter.articuloAdapter adapter = new com.example.tp4_grupo7.Adapter.articuloAdapter(context, listaProductos);
        lvProductos.setAdapter(adapter);
    }
}
