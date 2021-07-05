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
            "id_articulo int primary key NOT NULL," +
            "nombre_articulo text NOT NULL," +
            "precio real NOT NULL," +
            "existencia text NOT NULL," +
            "descripcion text NOT NULL," +
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
            "nombre_proveedor text NOT NULL," +
            "telefono text NOT NULL," +
            "direccion text NOT NULL);";

    String tabla_usuarios = "CREATE TABLE usuarios (" +
            "  id_usuario int primary key," +
            "  nombre_usuario text," +
            " contrasena text);";

    String tabla_ventas = "CREATE TABLE ventas (" +
            "id_venta int(11) primary key NOT NULL," +
            "fecha_venta datetime NOT NULL," +
            "monto int(11) NOT NULL," +
            "id_articulo text NOT NULL," +
            "id_Usuario int(11) NOT NULL," +
            "foreign key(id_articulo) references articulos(modelo)," +
            "foreign key(id_Usuario) references usuarios(id_usuario))";

    @Override
    public void onCreate(SQLiteDatabase Zapateria) {
        Zapateria.execSQL(tabla_usuarios);
        Zapateria.execSQL(tabla_cliente);
        Zapateria.execSQL(tabla_proveedores);
        Zapateria.execSQL(tabla_articulos);
        Zapateria.execSQL(tabla_compra);
        Zapateria.execSQL(tabla_ventas);

        //insersión de usuarios predeterminados
        ContentValues insercion_usuarios = new ContentValues();
        insercion_usuarios.put("id_usuario", 1);
        insercion_usuarios.put("nombre_usuario","Alejandra");
        insercion_usuarios.put("contrasena","123456789");
        Zapateria.insert("usuarios",null, insercion_usuarios);


        //inserción de proveedores predeterminados
        ContentValues insercion_proveedorLG = new ContentValues();
        insercion_proveedorLG.put("id_proveedor", 1);
        insercion_proveedorLG.put("nombre_proveedor", "LG");
        insercion_proveedorLG.put("telefono", "4771334567");
        insercion_proveedorLG.put("direccion", "Valle de los Insurgentes #123, León, Gto.");
        Zapateria.insert("proveedores", null, insercion_proveedorLG);

        ContentValues insercion_proveedorNike = new ContentValues();
        insercion_proveedorNike.put("id_proveedor", 2);
        insercion_proveedorNike.put("nombre_proveedor", "Nike");
        insercion_proveedorNike.put("telefono", "4775678035");
        insercion_proveedorNike.put("direccion", "Loma Bonita #186, León, Gto.");
        Zapateria.insert("proveedores", null, insercion_proveedorNike);

        ContentValues insercion_proveedorAdidas = new ContentValues();
        insercion_proveedorAdidas.put("id_proveedor",3);
        insercion_proveedorAdidas.put("nombre_proveedor", "Adidas");
        insercion_proveedorNike.put("telefono", "4753692230");
        insercion_proveedorNike.put("direccion", "Punto Verde #258, León, Gto.");
        Zapateria.insert("proveedores", null, insercion_proveedorAdidas);

        //Inserción de articulos de ejemplo
        String proveedores[] = {"LG","Nike","Adidas"};
        for(int i=1; i<4; i++){
            ContentValues articulos = new ContentValues();
            articulos.put("id_articulo",i);
            articulos.put("nombre_articulo","Tenis deportivo");
            articulos.put("precio",236);
            articulos.put("existencia","en existencia");
            articulos.put("descripcion", "Tenis para eventos deportivos en oferta");
            articulos.put("id_proveedor",proveedores[i-1]);
            Zapateria.insert("articulos",null,articulos);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}