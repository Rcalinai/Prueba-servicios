package controlador;

import connex.Conexion;
import modelo.proyectos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class proyectoControlador{
    public static List<proyectos> obtenerProyectos() throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            stm = cx.prepareStatement("SELECT * FROM proyectos");
        }
        ResultSet rs = stm.executeQuery();
        List<proyectos> proyectos = new ArrayList<>();
        while (rs.next()) {
            proyectos.add(new proyectos(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDate("fecha_inicio"),
                    rs.getDate("fecha_termino"),
                    rs.getInt("presupuesto"),
                    rs.getInt("gasto_actual")
            ));
        }
        Conexion.desconectar();
        return proyectos;
    }
    public static void actualizarGasto(int id, int gasto) throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            stm = cx.prepareStatement("UPDATE proyectos SET gastoAct = ? WHERE id = ?");
            stm.setInt(1, gasto);
            stm.setInt(2, id);
            stm.executeUpdate();
        }finally {
            Conexion.desconectar();
        }
    }

    public static proyectos obtenerProyecto(int id) throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            stm = cx.prepareStatement("SELECT * FROM proyectos WHERE id = ?");
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return new proyectos(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDate("fecha_inicio"),
                        rs.getDate("fecha_fin"),
                        rs.getInt("presupuesto"),
                        rs.getInt("gastoAct")
                );
            }
        }finally {
            Conexion.desconectar();
        }
        return null;
    }
}
