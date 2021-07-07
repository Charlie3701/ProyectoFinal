package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_HistorialVentas extends AppCompatActivity {
    private EditText busqueda;
    private TextView txtdatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__historial_ventas);
        busqueda = (EditText)findViewById(R.id.txtBuscarArticuloVendido);
        txtdatos = (TextView)findViewById(R.id.editTextTextMultiLine);

        try{
            AdminSQLiteOpenHelper conexion = new AdminSQLiteOpenHelper(this, "Zapateria",
                    null, 1);
            SQLiteDatabase BaseDeDatos = conexion.getWritableDatabase();

            Cursor fila = BaseDeDatos.rawQuery("select * from ventas", null);
            String ventas = "";
            if(fila.moveToFirst()){
                while(!fila.isAfterLast()) {
                    ventas+="id_venta: "+fila.getString(0)+"\n";
                    ventas+="fecha_venta "+fila.getString(1)+"\n";
                    ventas+="PRECIO: "+fila.getString(2)+"\n";
                    ventas+="id Articulo: "+fila.getString(3)+"\n";
                    ventas+="Vendedor: "+fila.getString(4)+" \n";
                    ventas+="-------------------------------------------\n";
                    fila.moveToNext();
                }
                fila.close();
                BaseDeDatos.close();
                txtdatos.setText(ventas);
                txtdatos.setKeyListener(null);
            } else{
                fila.close();
                BaseDeDatos.close();
                txtdatos.setText("No hay artículos en el inventario");
                txtdatos.setKeyListener(null);
            }
        } catch (SQLException e){
            Toast.makeText(this, "Error al mostrar el inventario", Toast.LENGTH_LONG).show();
        }
    }

    public void buscar(View view){
        String busquedaS = busqueda.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Zapateria"
                ,null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        if (!busquedaS.isEmpty()){

            Cursor fila = BaseDeDatos.rawQuery("select * from ventas where id_venta="+busquedaS,null);

            if(fila.moveToFirst()){
                txtdatos.setText(
                        fila.getString(0)+" "+fila.getString(1)+" "+fila.getString(2)+" "+fila.getString(3)+" "+fila.getString(4));
                txtdatos.setKeyListener(null);
                BaseDeDatos.close();
            } else {
                Toast.makeText(this,"No existe el artículo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }

        }else{
            Toast.makeText(this, "Debes introducir el ID de la venta", Toast.LENGTH_SHORT).show();
        }

    }

    public void regresar(View view){
        Intent principal = new Intent(this,MenuActivity.class);
        startActivity(principal);
    }
}