package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void Seleccion(View view){
        switch(view.getId()){
            case R.id.realizarVenta:
                Intent pantalla_ventas = new Intent(this, Activity_Ventas.class);
                startActivity(pantalla_ventas);
                break;
            case R.id.verInventario:
                Intent pantalla_inventario = new Intent(this, Activity_VerInventario.class);
                startActivity(pantalla_inventario);
                break;
            case R.id.modificarInventario:
                Intent pantalla_modificar = new Intent(this, Activity_ModificarInventario.class);
                startActivity(pantalla_modificar);
                break;
            case R.id.historialVentas:
                Intent pantalla_historial = new Intent(this, Activity_HistorialVentas.class);
                startActivity(pantalla_historial);
                break;
        }
    }
}