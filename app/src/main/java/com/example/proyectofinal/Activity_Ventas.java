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
import android.widget.TextView;
import android.widget.Toast;

public class Activity_Ventas extends AppCompatActivity {
    private EditText et_buscarArti, et_fecha, et_monto, et_articulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__ventas);
        et_buscarArti=(EditText)findViewById(R.id.txtBuscarArticulo);
        et_fecha=(EditText)findViewById(R.id.editTextDate);
        et_monto=(EditText)findViewById(R.id.editTextNumber);
        et_articulo=(EditText)findViewById(R.id.editTextTextPersonName);
    }

    public void buscarAñadir (View view) {
        String articulo = et_buscarArti.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Zapateria"
                , null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        if (!articulo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery("select modelo from articulos where modelo="+articulo,null);

            if(fila.moveToFirst()){
                et_articulo.setText(fila.getString(0));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this,"No existe el artículo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }


        }else{
            Toast.makeText(this, "Debes introducir el ID del artículo", Toast.LENGTH_SHORT).show();
        }
    }

    public void venta(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Zapateria"
                , null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        Encapsulado en = new Encapsulado();

        String idarticulo = et_articulo.getText().toString();
        String fecha= et_fecha.getText().toString();
        String monto = et_monto.getText().toString();
        int id_usuario = en.getID();
        int id_articulo = Integer.parseInt(idarticulo);
        int monto1 = Integer.parseInt(monto);

        int id_venta= buscarID(BaseDeDatos);

        if(!fecha.isEmpty() && !monto.isEmpty() && !idarticulo.isEmpty()) {
            ContentValues registro = new ContentValues();

            registro.put("id_venta", id_venta);
            registro.put("fecha_venta",fecha);
            registro.put("monto",monto1);
            registro.put("id_articulo",id_articulo);
            registro.put("id_Usuario", id_usuario);

            BaseDeDatos.insert("ventas",null,registro);

            BaseDeDatos.close();
            et_articulo.setText("");
            et_fecha.setText("");
            et_monto.setText("");
            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }


    }

    public int buscarID(SQLiteDatabase BaseDeDatos){
        int id_venta;
            Cursor fila = BaseDeDatos.rawQuery("select id_venta from ventas WHERE  (SELECT MAX(id_venta) FROM ventas) ",null);
            if(fila.moveToFirst()) {
                String idventa = fila.getString(0);
                if (!idventa.isEmpty()){
                    id_venta=Integer.parseInt(idventa);
                    fila.close();
                    return id_venta=id_venta+1;

                }
                fila.close();
                return 1;
            }
        fila.close();
        return 1;
    }

    public void regresar(View view){
        Intent principal = new Intent(this,MenuActivity.class);
        startActivity(principal);
    }
}