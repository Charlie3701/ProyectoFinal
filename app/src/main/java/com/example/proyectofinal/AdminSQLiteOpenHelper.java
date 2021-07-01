package com.example.proyectofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    String tabla_articulos="CREATE TABLE articulos (" +
            "id_articulo int(11) primary key NOT NULL," +
            "nombre_articulo char(30) NOT NULL," +
            "precio float NOT NULL," +
            "existencia int(11) NOT NULL," +
            "descripcion varchar(60) NOT NULL," +
            "id_proveedor int(11), " +
            "foreign key(id_proveedor) references proveedores(id_proveedor));";

    String tabla_cliente = "CREATE TABLE cliente (" +
            "id_cliente int(11) primary key NOT NULL," +
            "nombre_cliente char(30) NOT NULL," +
            "telefono char(10) NOT NULL," +
            "direccion char(10) NOT NULL);";

    String tabla_compra = "CREATE TABLE compra (" +
            "id_compra int(11) primary key NOT NULL," +
            "id_articulo int(11) NOT NULL," +
            "id_cliente int(11) NOT NULL," +
            "fecha_compra datetime NOT NULL," +
            "monto float NOT NULL," +
            "foreign key(id_articulo) references articulos(id_articulo)," +
            "foreign key(id_cliente) references cliente(id_cliente))";

    String tabla_proveedores = "CREATE TABLE proveedores (" +
            "id_proveedor int(11) primary key NOT NULL," +
            "nombre_proveedor char(30) NOT NULL," +
            "telefono char(10) NOT NULL," +
            "direccion char(60) NOT NULL);";

    String tabla_usuarios = "CREATE TABLE usuarios (" +
            "  id_usuario int primary key," +
            "  nombre_usuario text," +
            " contrasena text);";

    String tabla_ventas = "CREATE TABLE ventas (" +
            "id_venta int(11) primary key NOT NULL," +
            "fecha_venta datetime NOT NULL," +
            "monto int(11) NOT NULL," +
            "id_articulo int(11) NOT NULL," +
            "id_Usuario int(11) NOT NULL," +
            "foreign key(id_articulo) references articulos(id_articulo)," +
            "foreign key(id_Usuario) references usuarios(id_usuario))";

    @Override
    public void onCreate(SQLiteDatabase Zapateria) {
        Zapateria.execSQL(tabla_usuarios);
        Zapateria.execSQL(tabla_cliente);
        Zapateria.execSQL(tabla_proveedores);
        Zapateria.execSQL(tabla_articulos);
        Zapateria.execSQL(tabla_compra);
        Zapateria.execSQL(tabla_ventas);

        ContentValues insercion_usuarios = new ContentValues();
        insercion_usuarios.put("id_usuario", 1);
        insercion_usuarios.put("nombre_usuario","Alejandra");
        insercion_usuarios.put("contrasena","123456789");
        Zapateria.insert("usuarios",null, insercion_usuarios);

        insercion_usuarios.put("id_venta", 1);
        insercion_usuarios.put("fecha_venta", 1);
        insercion_usuarios.put("monto", 100);
        insercion_usuarios.put("id_articulo", 1);
        insercion_usuarios.put("id_Usuario", 1);
        Zapateria.insert("ventas",null, insercion_usuarios);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}