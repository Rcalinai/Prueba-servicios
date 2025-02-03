package controlador;

import connex.Conexion;
import modelo.empleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class empleadoControlador {

    public static boolean login(String user, String password) throws SQLException {
        // Usamos try-with-resources para gestionar automáticamente los recursos
        try (Connection cx = Conexion.conectar("root", "");
             PreparedStatement stm = cx.prepareStatement("SELECT contraseña FROM empleados WHERE nombre = ?")) {

            // Establece el parámetro en la consulta SQL
            stm.setString(1, user);

            // Ejecuta la consulta
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    // Obtén la contraseña almacenada en la base de datos
                    String storedPassword = rs.getString("contraseña");

                    // Compara la contraseña ingresada con la almacenada
                    return password.equals(storedPassword);
                } else {
                    // No se encontró el usuario
                    return false;
                }
            }
        } catch (SQLException e) {
            // Maneja cualquier excepción SQL
            e.printStackTrace();
            throw e;
        }finally {
            Conexion.desconectar();
        }
    }

    public static List<empleados> obtenerEmpleados() throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            stm = cx.prepareStatement("SELECT * FROM empleados");
            ResultSet rs = stm.executeQuery();

            List<empleados> empleados = new ArrayList<>();
            while (rs.next()) {
                empleados.add(new empleados(
                        rs.getInt("id"),
                        rs.getInt("foto"),  
                        rs.getString("contraseña"),
                        rs.getInt("proyecto_id"),
                        rs.getString("salario"),
                        rs.getString("puesto"),
                        rs.getString("nombre")
                ));
            }
            return empleados;
        }finally {
            Conexion.desconectar();
        }
    }
    public static empleados obtenerEmpleado(String nombre) throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            stm = cx.prepareStatement("SELECT * FROM empleados WHERE nombre = ?");
            stm.setString(1, nombre);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                empleados empleado = new empleados(
                        rs.getInt("id"),
                        rs.getInt("foto"),
                        rs.getString("contraseña"),
                        rs.getInt("proyecto_id"),
                        rs.getString("salario"),
                        rs.getString("puesto"),
                        rs.getString("nombre")
                );
                return empleado;
            } else {
                return null;
            }
        }finally {
            Conexion.desconectar();
        }
    }
    public static boolean registrarEmpleado(empleados empleado) throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            stm = cx.prepareStatement("INSERT INTO empleados (id, foto, contraseña, proyecto_id, salario, puesto, nombre) VALUES (?, ?, ?, ?, ?, ?, ?)");
            stm.setInt(1, empleado.getId());
            stm.setInt(2, empleado.getFotoInt());
            stm.setString(3, empleado.getContrasenia());
            stm.setInt(4, empleado.getId_proyecto());
            stm.setString(5, empleado.getSalario());
            stm.setString(6, empleado.getPuesto());
            stm.setString(7, empleado.getNombre());
            int filasAfectadas = stm.executeUpdate();
            return filasAfectadas > 0;
        }finally {
            Conexion.desconectar();
        }
    }

    public static boolean eliminarEmpleado(int id) throws SQLException {
        PreparedStatement stm;
        try (Connection cx = Conexion.conectar("root", "")) {
            stm = cx.prepareStatement("DELETE FROM empleados WHERE id = ?");
            stm.setInt(1, id);
            int filasAfectadas = stm.executeUpdate();
            return filasAfectadas > 0;
        }
    }
}
