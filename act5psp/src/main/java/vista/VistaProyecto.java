package vista;

import controlador.proyectoControlador;
import modelo.empleados;
import modelo.proyectos;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class VistaProyecto {
    private JFrame frame;
    private JTextField nombreProyecto;
    private JTextField presupuesto;
    private JTextField gastoActual;
    private JTextField incrementoGasto;
    private JButton btnActualizar;
    private JButton btnVolver;

    private proyectos proyectoActual;

    public VistaProyecto() throws SQLException {
        empleados empleado = empleados.getEmpleado_actual();
        proyectos proyecto = proyectoControlador.obtenerProyecto(empleado.getId_proyecto());
        proyectoActual = proyecto;

        // Crear la ventana
        frame = new JFrame("Vista del Proyecto");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel central para los campos
        JPanel panelCampos = new JPanel(new GridLayout(4, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCampos.add(new JLabel("Nombre del Proyecto:"));
        nombreProyecto = new JTextField(proyectoActual.getNombre());
        nombreProyecto.setEditable(false);
        panelCampos.add(nombreProyecto);

        panelCampos.add(new JLabel("Presupuesto:"));
        presupuesto = new JTextField(String.valueOf(proyectoActual.getPresupuesto()));
        presupuesto.setEditable(false);
        panelCampos.add(presupuesto);

        panelCampos.add(new JLabel("Gasto Actual:"));
        gastoActual = new JTextField(String.valueOf(proyectoActual.getGasto_actual()));
        gastoActual.setEditable(false);
        panelCampos.add(gastoActual);

        panelCampos.add(new JLabel("Incrementar Gasto:"));
        incrementoGasto = new JTextField();
        panelCampos.add(incrementoGasto);

        frame.add(panelCampos, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnActualizar = new JButton("Actualizar Gasto");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);
        frame.add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón "Actualizar Gasto"
        btnActualizar.addActionListener(e -> actualizarGasto());

        // Acción del botón "Volver"
        btnVolver.addActionListener(e -> volverAlMainFrame());

        // Mostrar la ventana
        frame.setVisible(true);
    }

    private void actualizarGasto() {
        try {
            // Leer el valor ingresado
            double incremento = Double.parseDouble(incrementoGasto.getText());

            // Validar que no exceda el presupuesto
            if (proyectoActual.getGasto_actual() + incremento > proyectoActual.getPresupuesto()) {
                JOptionPane.showMessageDialog(frame, "El gasto no puede superar el presupuesto.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Actualizar el gasto
                proyectoActual.setGasto_actual((int) (proyectoActual.getGasto_actual() + incremento));
                gastoActual.setText(String.valueOf(proyectoActual.getGasto_actual()));

                // Actualizar en la base de datos (si aplica)
                proyectoControlador.actualizarGasto(proyectoActual.getId(), proyectoActual.getGasto_actual());

                JOptionPane.showMessageDialog(frame, "Gasto actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, introduce un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void volverAlMainFrame() {
        frame.dispose(); // Cierra la ventana actual
        SwingUtilities.invokeLater(() -> {
            try {
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al volver al MainFrame: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
