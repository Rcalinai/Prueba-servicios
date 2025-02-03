package connex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String bd = "empresa";
    private static String url = "jdbc:mysql://localhost:3305/";
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static Connection cx;


    public static Connection conectar(String user, String password) {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + bd, user, password);
            System.out.println("Conectado a la base de datos " + bd);
        } catch (Exception e) {
            System.err.println("Error al conectar: " + e.getMessage());
            cx = null;
        }
        return cx;
    }

    public static void desconectar() {
        try {
            if (cx != null && !cx.isClosed()) {
                cx.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al desconectar: " + e.getMessage());
        }
    }
}
