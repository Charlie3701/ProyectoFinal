package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Activity_ModificarInventario extends AppCompatActivity {
    private EditText modelo, nombre, precio, existencia, descripcion, proveedor;
    private RadioButton rbInsertar, rbModificar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__modificar_inventario);

        modelo = (EditText) findViewById(R.id.Modelo);
        nombre=(EditText) findViewById(R.id.Nombre);
        precio=(EditText) findViewById(R.id.Precio);
        existencia=(EditText) findViewById(R.id.Existencia);
        descripcion=(EditText) findViewById(R.id.Descripcion);
        proveedor=(EditText) findViewById(R.id.Proveedor);

        rbInsertar =(RadioButton)findViewById(R.id.Insertar);
        rbModificar = (RadioButton)findViewById(R.id.Modificar);
    }

    public void seleccion(View view) {
        String Modelo = modelo.getText().toString();
        String Nombre_ = nombre.getText().toString();
        String Precio_ = precio.getText().toString();
        String Existencia_ = existencia.getText().toString();
        String Descripcion_ =descripcion.getText().toString();
        String Proveedor_ = proveedor.getText().toString();

        int id_articulo=Integer.parseInt(Modelo);
        int Cantidad =Integer.parseInt(Existencia_);
        float Precio1=Float.parseFloat(Precio_);
        int id_proveedor=Integer.parseInt(Proveedor_);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Zapateria"
                ,null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        if(rbInsertar.isChecked()==true){
            try {
                Cursor fila = BaseDeDatos.rawQuery("select * from articulos where  id_articulo = "
                        + id_articulo, null);
                if(fila.getCount()==0) {
                    fila.close();
                    BaseDeDatos.execSQL("INSERT INTO articulos VALUES ("+id_articulo+",'"+Nombre_+"','"+Precio1+"','"+Cantidad+"','"+Descripcion_+"','"+id_proveedor+"')");
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
        }
        else if(rbModificar.isChecked()==true) {
            try {
                Cursor fila = BaseDeDatos.rawQuery("select * from articulos where  modelo= '"
                        + Modelo + "'", null);
                if (fila.getCount() != 0) {

                    modelo.setText(fila.getString(1));
                    nombre.setText(fila.getString(2));
                    precio.setText(fila.getString(3));
                    existencia.setText(fila.getString(4));
                    descripcion.setText(fila.getString(5));
                    fila.close();
                    BaseDeDatos.execSQL("UPDATE  articulos set id_articulo='" + id_articulo + "' nombre_articulo='" + Nombre_ + "',precio='" + Precio1 + "'," +
                            "existencia='" + Cantidad + "', " + " descripcion='" + Descripcion_ + "', id_proveedor='" + id_proveedor + "' ");

                    Toast.makeText(this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
                    BaseDeDatos.close();
                    modelo.setText("");
                    nombre.setText("");
                    descripcion.setText("");
                    precio.setText("");
                    existencia.setText("");

                } else {
                    Toast.makeText(this, "El artículo no existe en la base de datos", Toast.LENGTH_SHORT).show();
                    BaseDeDatos.close();
                }
            } catch (SQLException e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                BaseDeDatos.close();
                modelo.setText("");
                nombre.setText("");
                descripcion.setText("");
                precio.setText("");
                existencia.setText("");
            }
        } else
            Toast.makeText(this,"Selecciona la operación a realizar",Toast.LENGTH_SHORT).show();
    }

    public void regresar(View view){
        Intent principal = new Intent(this,MenuActivity.class);
        startActivity(principal);
    }
}