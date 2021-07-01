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

public class Activity_ModificarInventario extends AppCompatActivity {
    private EditText modelo, nombre, precio, existencia, descripcion, proveeddor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__modificar_inventario);

        modelo = (EditText) findViewById(R.id.Modelo);
        nombre=(EditText) findViewById(R.id.Nombre);
        precio=(EditText) findViewById(R.id.Precio);
        existencia=(EditText) findViewById(R.id.Existencia);
        descripcion=(EditText) findViewById(R.id.Descripcion);
    }

    public void seleccion(View view) {
        String Modelo = modelo.getText().toString();
        String Nombre_p = nombre.getText().toString();
        String Precio = precio.getText().toString();
        String Existencia = existencia.getText().toString();
        String Descripcion =descripcion.getText().toString();
        //String Proveedor = proveedor.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Zapateria"
                ,null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();


        switch (view.getId()) {
            //float Precio1=Float.parseFloat(Precio);
            case R.id.Insertar:

                try {
                    Cursor fila = BaseDeDatos.rawQuery("select * from articulos where  id_articulo= '"
                            + Modelo , null);
                    if(fila.getCount()==0) {
                        fila.close();
                     BaseDeDatos.execSQL("INSERT INTO articulos VALUES ('"+Modelo+"','"+Nombre_p+"','"+precio+"','"+existencia+"','"+descripcion+"')");
                        Toast.makeText(this, "Insersión exitosa", Toast.LENGTH_SHORT).show();
                        BaseDeDatos.close();

                    } else{
                        Toast.makeText(this, "Id invalido, ya existente en la base de datos", Toast.LENGTH_SHORT).show();
                        BaseDeDatos.close();
                    }
                } catch(SQLException e){
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    BaseDeDatos.close();
                    modelo.setText("");
                    nombre.setText("");
                    descripcion.setText("");
                    precio.setText("");
                    existencia.setText("");
                }


                break;
            case R.id.Modificar:
                try {

                Cursor fila = BaseDeDatos.rawQuery("select * from articulos where  id_articulo= '"
                        + Modelo , null);
                if(fila.getCount()!=0) {

                    modelo.setText(fila.getString(1));
                    nombre.setText(fila.getString(2));
                    precio.setText(fila.getString(3));
                    existencia.setText(fila.getString(4));
                    descripcion.setText(fila.getString(5));
                    fila.close();
                    BaseDeDatos.execSQL("UPDATE  articulos set id_articulo='"+modelo+"' nombre_articulo='"+nombre+"',precio='"+precio+"', existencia='"+existencia+"',  descripcion='"+descripcion+"' ");
                    Toast.makeText(this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
                    BaseDeDatos.close();
                    modelo.setText("");
                    nombre.setText("");
                    descripcion.setText("");
                    precio.setText("");
                    existencia.setText("");

                } else{
                    Toast.makeText(this, "El artículo no existe en la base de datos", Toast.LENGTH_SHORT).show();
                    BaseDeDatos.close();
                }
        } catch(SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            BaseDeDatos.close();
            modelo.setText("");
            nombre.setText("");
            descripcion.setText("");
            precio.setText("");
            existencia.setText("");
        }


        break;
        }
    }

    public void regresar(View view){
        Intent principal = new Intent(this,MenuActivity.class);
        startActivity(principal);
    }
}