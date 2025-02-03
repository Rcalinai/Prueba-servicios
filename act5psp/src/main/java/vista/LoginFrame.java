package vista;

import modelo.empleados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame extends JFrame {
    // Componentes de la interfaz
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnSalir;

    public LoginFrame() {
        // Configuración básica de la ventana
        setTitle("Inicio de Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(panel);

        // Panel para los campos de entrada
        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        panel.add(panelCampos, BorderLayout.CENTER);

        // Etiquetas y campos de texto
        JLabel lblUsuario = new JLabel("Usuario:");
        txtUsuario = new JTextField();
        JLabel lblPassword = new JLabel("Contraseña:");
        txtPassword = new JPasswordField();

        panelCampos.add(lblUsuario);
        panelCampos.add(txtUsuario);
        panelCampos.add(lblPassword);
        panelCampos.add(txtPassword);

        // Panel para los botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnLogin = new JButton("Iniciar Sesión");
        btnSalir = new JButton("Salir");
        panelBotones.add(btnLogin);
        panelBotones.add(btnSalir);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Eventos de los botones
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    iniciarSesion();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
    }

    // Método para manejar el inicio de sesión
    private void iniciarSesion() throws SQLException {
        String usuario = txtUsuario.getText();
        String password = new String(txtPassword.getPassword());


        if (usuario.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }else{
            boolean valido = controlador.empleadoControlador.login(usuario, password);
            empleados empleado = controlador.empleadoControlador.obtenerEmpleado(usuario);
            empleados.setEmpleado_actual(empleado);
            if (valido) {
                abrirVentanaPrincipal();
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }


    }

    // Método para salir de la aplicación
    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que quieres salir?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Método para abrir la ventana principal después de iniciar sesión
    private void abrirVentanaPrincipal() throws SQLException {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
        dispose(); // Cerrar el LoginFrame
    }


}

