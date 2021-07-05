package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class Activity_VerInventario extends AppCompatActivity {
    EditText pantalla, idArticulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ver_inventario);
        pantalla=(EditText)findViewById(R.id.txtVerInventario);
        idArticulo =(EditText)findViewById(R.id.BuscarArticulo);
        try{
            AdminSQLiteOpenHelper conexion = new AdminSQLiteOpenHelper(this, "Zapateria",
                    null, 1);
            SQLiteDatabase BaseDeDatos = conexion.getWritableDatabase();

            Cursor fila = BaseDeDatos.rawQuery("select * from articulos", null);
            String inventario = "";
            if(fila.moveToFirst()){
                while(!fila.isAfterLast()) {
                    inventario+="ID Artículo: "+fila.getString(0)+"\n";
                    inventario+="NOMBRE DEL ARTICULO: "+fila.getString(1)+"\n";
                    inventario+="PRECIO: "+fila.getString(2)+"\n";
                    inventario+="EXISTENCIA: "+fila.getString(3)+"\n";
                    inventario+="DESCRIPCION: "+fila.getString(4)+"\n";
                    inventario+="ID DEL PROVEEDOR: "+fila.getString(5)+"\n";
                    inventario+="-------------------------------\n";
                    fila.moveToNext();
                }
                fila.close();
                BaseDeDatos.close();
                pantalla.setText(inventario);
                pantalla.setKeyListener(null);
            } else{
                fila.close();
                BaseDeDatos.close();
                pantalla.setText("No hay artículos en el inventario");
                pantalla.setKeyListener(null);
            }
        } catch (SQLException e){
            Toast.makeText(this, "Error al mostrar el inventario", Toast.LENGTH_LONG).show();
        }
    }

    public void ConsultarPorIDArticulo(View view) {
        try {
            AdminSQLiteOpenHelper conexion = new AdminSQLiteOpenHelper(this, "Zapateria",
                    null, 1);
            SQLiteDatabase BaseDeDatos = conexion.getWritableDatabase();

            String idArticulo_txt = idArticulo.getText().toString();
            if(!idArticulo_txt.isEmpty()){
                String registro = "";
                Cursor fila = BaseDeDatos.rawQuery("select * from articulos where id_articulo="
                                + idArticulo_txt, null);
                if (fila.moveToFirst()) {
                    while (!fila.isAfterLast()) {
                        registro += "ID ARTÍCULO: " + fila.getString(0) + "\n";
                        registro += "NOMBRE DEL ARTICULO: " + fila.getString(1) + "\n";
                        registro += "PRECIO: " + fila.getString(2) + "\n";
                        registro += "EXISTENCIA: " + fila.getString(3) + "\n";
                        registro += "DESCRIPCION: " + fila.getString(4) + "\n";
                        registro += "ID DEL PROVEEDOR: " + fila.getString(5) + "\n";
                        registro += "-------------------------------\n";
                        fila.moveToNext();
                    }
                    fila.close();
                    BaseDeDatos.close();
                    pantalla.setText(registro);
                    pantalla.setKeyListener(null);
                } else {
                    fila.close();
                    BaseDeDatos.close();
                    pantalla.setText("ARTÍCULO NO ENCONTRADO");
                    pantalla.setKeyListener(null);
                }
            }
            else
                Toast.makeText(this, "Ingrese el Modelo del Artículo a Buscar",
                        Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void RegresarMenu(View view){
        Intent regresar = new Intent(this, MenuActivity.class);
        startActivity(regresar);
    }
}