package vista;

import modelo.certificaciones;

import controlador.certificacionesControlador;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class VistaTablaCertificados {
    private JFrame frame;
    private JTable table;
    private JButton btnVolver;

    public VistaTablaCertificados() throws SQLException {
        // Crear la ventana
        frame = new JFrame("Lista de Certificados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Crear la tabla y modelo
        String[] columnNames = {"ID", "Nombre", "Fecha de Obtención", "Empleado ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Cargar los datos
        cargarDatos(tableModel);

        // Agregar la tabla a un scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Botón para volver
        JPanel panelBotones = new JPanel();
        btnVolver = new JButton("Volver");
        panelBotones.add(btnVolver);
        frame.add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón "Volver"
        btnVolver.addActionListener(e -> volverAlMainFrame());

        // Mostrar la ventana
        frame.setVisible(true);
    }

    private void cargarDatos(DefaultTableModel tableModel) throws SQLException {
        // Llamar al controlador para obtener los datos de los certificados
        List<certificaciones> certificados = certificacionesControlador.obtenerCertificaciones();

        // Agregar las filas al modelo de la tabla
        for (certificaciones certificado : certificados) {
            Object[] rowData = {
                    certificado.getId(),
                    certificado.getNombre(),
                    certificado.getFecha_obtencion(),
                    certificado.getId_empleado()
            };
            tableModel.addRow(rowData);
        }
    }

    private void volverAlMainFrame() {
        frame.dispose(); // Cierra la ventana actual
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
