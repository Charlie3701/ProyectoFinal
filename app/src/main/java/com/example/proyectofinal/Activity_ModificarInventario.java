package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
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

        if(!Modelo.isEmpty() && !Existencia_.isEmpty() && !Precio_.isEmpty() && !Proveedor_.isEmpty()) {
            int id_articulo = Integer.parseInt(Modelo);
            int Cantidad = Integer.parseInt(Existencia_);
            float Precio1 = Float.parseFloat(Precio_);
            int id_proveedor = Integer.parseInt(Proveedor_);

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Zapateria"
                    , null, 1);
            SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

            if (rbInsertar.isChecked() == true) {
                try {
                    Cursor fila = BaseDeDatos.rawQuery("select * from articulos where  id_articulo = "
                            + id_articulo, null);
                    if (fila.getCount() == 0) {
                        fila.close();
                        BaseDeDatos.execSQL("INSERT INTO articulos VALUES (" + id_articulo + ",'" + Nombre_ + "','" + Precio1 + "','" + Cantidad + "','" + Descripcion_ + "','" + id_proveedor + "')");
                        Toast.makeText(this, "Insersión exitosa", Toast.LENGTH_SHORT).show();
                        BaseDeDatos.close();
                        modelo.setText("");
                        nombre.setText("");
                        proveedor.setText("");
                        descripcion.setText("");
                        precio.setText("");
                        existencia.setText("");
                    } else {
                        Toast.makeText(this, "Id invalido, ya existente en la base de datos", Toast.LENGTH_SHORT).show();
                        BaseDeDatos.close();
                        modelo.setText("");
                        nombre.setText("");
                        proveedor.setText("");
                        descripcion.setText("");
                        precio.setText("");
                        existencia.setText("");
                    }
                } catch (SQLException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    BaseDeDatos.close();
                    modelo.setText("");
                    nombre.setText("");
                    proveedor.setText("");
                    descripcion.setText("");
                    precio.setText("");
                    existencia.setText("");
                }
            } else if (rbModificar.isChecked() == true) {
                try {
                    if (!Modelo.isEmpty() && !Nombre_.isEmpty() && !Precio_.isEmpty() && !Existencia_.isEmpty()
                            && !Descripcion_.isEmpty() && !Proveedor_.isEmpty()) {
                        ContentValues modificar = new ContentValues();
                        modificar.put("id_articulo", id_articulo);
                        modificar.put("nombre_articulo", Nombre_);
                        modificar.put("precio", Precio1);
                        modificar.put("existencia", Cantidad);
                        modificar.put("descripcion", Descripcion_);
                        modificar.put("id_proveedor", id_proveedor);
                        int registros_modificados = BaseDeDatos.update("articulos", modificar,
                                "id_articulo =" + id_articulo, null);
                        BaseDeDatos.close();
                        modelo.setText("");
                        nombre.setText("");
                        descripcion.setText("");
                        precio.setText("");
                        existencia.setText("");
                        proveedor.setText("");
                        if (registros_modificados == 1)
                            Toast.makeText(this, "Modificación exitosa", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(this, "El Artículo no existe", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "Ingresa los datos solicitados", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    BaseDeDatos.close();
                    modelo.setText("");
                    nombre.setText("");
                    descripcion.setText("");
                    precio.setText("");
                    proveedor.setText("");
                    existencia.setText("");
                }
            } else if (rbInsertar.isChecked() != true && rbModificar.isChecked() != true) {
                Toast.makeText(this, "Selecciona la operación a realizar", Toast.LENGTH_SHORT).show();
            }
        } else{
            Toast.makeText(this, "Debes selecciona y llenar los datos", Toast.LENGTH_SHORT).show();
        }
    }

    public void regresar(View view){
        Intent principal = new Intent(this,MenuActivity.class);
        startActivity(principal);
    }
}