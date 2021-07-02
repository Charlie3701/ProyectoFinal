package com.example.proyectofinal;

public class Encapsulado {
    private static int id_usuario;

    public void setID(int usuario){
        id_usuario=usuario;
    }

    public int getID(){
        return id_usuario;
    }
}
