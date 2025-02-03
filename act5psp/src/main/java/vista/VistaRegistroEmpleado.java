package vista;

import modelo.empleados;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class VistaRegistroEmpleado {

    private JFrame frame;
    private JTextField txtId;
    private JTextField txtFoto;
    private JTextField txtContrasenia;
    private JTextField txtIdProyecto;
    private JTextField txtSalario;
    private JTextField txtPuesto;
    private JTextField txtNombre;
    private JButton btnRegistrar;
    private JButton btnVolver;

    public VistaRegistroEmpleado() {
        // Crear la ventana
        frame = new JFrame("Registrar Nuevo Empleado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel central para los campos
        JPanel panelCampos = new JPanel(new GridLayout(8, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCampos.add(new JLabel("ID:"));
        txtId = new JTextField();
        panelCampos.add(txtId);

        panelCampos.add(new JLabel("Foto (número):"));
        txtFoto = new JTextField();
        panelCampos.add(txtFoto);

        panelCampos.add(new JLabel("Contraseña:"));
        txtContrasenia = new JTextField();
        panelCampos.add(txtContrasenia);

        panelCampos.add(new JLabel("ID Proyecto:"));
        txtIdProyecto = new JTextField();
        panelCampos.add(txtIdProyecto);

        panelCampos.add(new JLabel("Salario:"));
        txtSalario = new JTextField();
        panelCampos.add(txtSalario);

        panelCampos.add(new JLabel("Puesto:"));
        txtPuesto = new JTextField();
        panelCampos.add(txtPuesto);

        panelCampos.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelCampos.add(txtNombre);

        frame.add(panelCampos, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnRegistrar = new JButton("Registrar");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVolver);
        frame.add(panelBotones, BorderLayout.SOUTH);

        // Acción del botón "Registrar"
        btnRegistrar.addActionListener(e -> registrarEmpleado());

        // Acción del botón "Volver"
        btnVolver.addActionListener(e -> volverAlMainFrame());

        // Mostrar la ventana
        frame.setVisible(true);
    }

    private void registrarEmpleado() {
        try {
            // Obtener los datos del formulario
            Integer id = Integer.parseInt(txtId.getText());
            Integer foto = Integer.parseInt(txtFoto.getText());
            String contrasenia = txtContrasenia.getText();
            Integer idProyecto = Integer.parseInt(txtIdProyecto.getText());
            String salario = txtSalario.getText();
            String puesto = txtPuesto.getText();
            String nombre = txtNombre.getText();

            // Validar que los campos no estén vacíos
            if (contrasenia.isEmpty() || salario.isEmpty() || puesto.isEmpty() || nombre.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear el objeto Empleado
            empleados nuevoEmpleado = new empleados(id, foto, contrasenia, idProyecto, salario, puesto, nombre);


            boolean exito = controlador.empleadoControlador.registrarEmpleado(nuevoEmpleado);

            if (exito) {
                JOptionPane.showMessageDialog(frame, "Empleado registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "Error al registrar el empleado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, ingrese números válidos en los campos ID, Foto y ID Proyecto.", "Error de formato", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void volverAlMainFrame() {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        frame.dispose();
    }

}