package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
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
        txtdatos = (TextView)findViewById(R.id.textView);
    }

    public void buscar(View view){
        String busquedaS = busqueda.getText().toString();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Zapateria"
                ,null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        if (!busquedaS.isEmpty()){

            Cursor fila = BaseDeDatos.rawQuery("select * from ventas where id_venta="+busquedaS,null);

            if(fila.moveToFirst()){
                txtdatos.setText("ID   Fecha Venta    Monto ID Articulo  ID Usuario \\n"+
                        fila.getString(0)+fila.getString(1)+fila.getString(2)+fila.getString(3));
                BaseDeDatos.close();
            } else {
                Toast.makeText(this,"No existe el artículo", Toast.LENGTH_SHORT).show();
                BaseDeDatos.close();
            }

        }else{
            Toast.makeText(this, "Debes introducir el ID del artículo", Toast.LENGTH_SHORT).show();
        }

    }

    public void regresar(View view){
        Intent principal = new Intent(this,MainActivity.class);
        startActivity(principal);
    }
}