package vista;

import modelo.empleados;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.sql.SQLException;

public class VistaEliminarEmpleados {
    private JFrame frame;
    private JTable table;
    private JButton btnEliminar;
    private JButton btnVolver;

    public VistaEliminarEmpleados() throws SQLException {
        // Crear la ventana
        frame = new JFrame("Eliminar Empleados");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Crear la tabla y el modelo
        String[] columnNames = {"ID", "Nombre", "Puesto", "Salario"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);

        // Cargar los datos
        cargarDatos(tableModel);

        // Agregar la tabla a un scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Crear el panel de botones
        JPanel panelBotones = new JPanel();
        btnEliminar = new JButton("Eliminar");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnEliminar);
        panelBotones.add(btnVolver);
        frame.add(panelBotones, BorderLayout.SOUTH);

        // Acción para eliminar empleados
        btnEliminar.addActionListener(e -> {
            try {
                eliminarEmpleado(tableModel);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        // Acción para volver al menú principal
        btnVolver.addActionListener(e -> volverAlMainFrame());

        // Mostrar la ventana
        frame.setVisible(true);
    }

    private void cargarDatos(DefaultTableModel tableModel) throws SQLException {
        // Llamar al controlador para obtener los empleados
        List<empleados> empleados = controlador.empleadoControlador.obtenerEmpleados();

        // Agregar los datos al modelo de la tabla
        for (empleados empleado : empleados) {
            Object[] rowData = {
                    empleado.getId(),
                    empleado.getNombre(),
                    empleado.getPuesto(),
                    empleado.getSalario()
            };
            tableModel.addRow(rowData);
        }
    }

    private void eliminarEmpleado(DefaultTableModel tableModel) throws SQLException {
        // Obtener la fila seleccionada
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Selecciona un empleado para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Confirmar la eliminación
        int confirm = JOptionPane.showConfirmDialog(
                frame,
                "¿Estás seguro de que deseas eliminar este empleado?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            // Obtener el ID del empleado seleccionado
            int empleadoId = (int) tableModel.getValueAt(selectedRow, 0);

            // Llamar al controlador para eliminar el empleado
            boolean eliminado = controlador.empleadoControlador.eliminarEmpleado(empleadoId);

            if (eliminado) {
                // Eliminar la fila del modelo de la tabla
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "Empleado eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Error al eliminar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void volverAlMainFrame() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        frame.dispose();
    }
}
