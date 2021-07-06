package com.example.proyectofinal;

public class Encapsulado {
    private static int id_usuario, existencia;

    public void setID(int usuario){
        id_usuario=usuario;
    }

    public int getID(){
        return id_usuario;
    }

    public void setExistencia(int existencia1){
        existencia=existencia1;
    }

    public int getExistencia(){
        return existencia;
    }
}
