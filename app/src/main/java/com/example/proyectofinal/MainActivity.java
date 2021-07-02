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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText usuario,contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (EditText) findViewById(R.id.txtUsuario);
        contrasena = (EditText) findViewById(R.id.txtContraseña);
    }

    public void Login(View view){
        String usuario_txt = usuario.getText().toString();
        String contrasena_txt = contrasena.getText().toString();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "Zapateria"
                ,null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        if(!usuario_txt.isEmpty() && !contrasena_txt.isEmpty()){
            try {
                Cursor fila = BaseDeDatos.rawQuery("select id_usuario from usuarios where nombre_usuario = '"
                        + usuario_txt + "' and contrasena = '" + contrasena_txt+"'", null);
                if(fila.getCount()!=0) {
                    Encapsulado en = new Encapsulado();
                    fila.moveToFirst();
                    en.setID(Integer.parseInt(fila.getString(0)));
                    fila.close();
                    Intent ingreso = new Intent(this, MenuActivity.class);
                    BaseDeDatos.close();
                    startActivity(ingreso);
                } else{
                    Toast.makeText(this, "Usuario y/o Contraseña Incorrectos", Toast.LENGTH_SHORT).show();
                    BaseDeDatos.close();
                }
            } catch(SQLException e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                BaseDeDatos.close();
                usuario.setText("");
                contrasena.setText("");
                }
            } else {
            Toast.makeText(this, "Ingrese los datos solicitados",Toast.LENGTH_SHORT).show();
            BaseDeDatos.close();
        }
    }
}