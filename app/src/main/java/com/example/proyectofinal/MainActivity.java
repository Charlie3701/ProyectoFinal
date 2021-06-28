package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        usuario = (EditText)findViewById(R.id.txtUsuario);
        contrasena = (EditText)findViewById(R.id.txtContraseña);
    }

    public void Login(View view){
        String usuario_txt = usuario.getText().toString();
        String contrasena_txt = contrasena.getText().toString();

        if(usuario_txt.equals("Alejandra")&& contrasena_txt.equals("123")){
            Intent ingreso = new Intent(this, MenuActivity.class);
            startActivity(ingreso);
        }
        else
            Toast.makeText(this, "Usuario y/o Contraseña Incorrectos",Toast.LENGTH_SHORT).show();
    }
}