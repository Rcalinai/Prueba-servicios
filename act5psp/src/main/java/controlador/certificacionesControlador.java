package controlador;

import connex.Conexion;
import modelo.certificaciones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class certificacionesControlador {

    public static List<certificaciones> obtenerCertificaciones() throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            String query = "SELECT * FROM certificaciones";
            stm = cx.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            List<certificaciones> certificaciones = new ArrayList<>();
            while (rs.next()) {
                certificaciones.add(new certificaciones(
                        rs.getInt("id"),
                        rs.getInt("empleado_id"),
                        rs.getDate("fecha_obtencion"),
                        rs.getString("nombre")
                ));
            }
            Conexion.desconectar();
            return certificaciones;
        }


    }
}
