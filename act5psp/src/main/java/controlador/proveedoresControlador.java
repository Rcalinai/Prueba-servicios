package controlador;

import connex.Conexion;
import modelo.proveedores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class proveedoresControlador {
    public static List<proveedores> obtenerProveedores() throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            stm = cx.prepareStatement("SELECT * FROM proveedores");
        }
        ResultSet rs = stm.executeQuery();
        List<proveedores> proveedores = new ArrayList<>();
        while (rs.next()) {
            proveedores.add(new proveedores(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("especialidad"),
                    rs.getInt("id_proyecto")
            ));
        }
        Conexion.desconectar();
        return proveedores;
    }
}
