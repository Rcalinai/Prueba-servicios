package vista;


import controlador.empleadoControlador;
import modelo.empleados;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.sql.SQLException;
import java.util.List;


public class VistaIndividual {
    private JFrame frame;
    private JTextField Id;
    private JTextField nombre;
    private JTextField puesto;
    private JTextField salario;
    private JLabel foto; // Para mostrar la foto
    private JButton btnAnterior;
    private JButton btnSiguiente;
    private JButton btnPrimero;
    private JButton btnUltimo;
    private JButton btnVolver;

    private List<empleados> registros;
    private int indiceActual;

    public VistaIndividual() throws SQLException {
        // Inicializa la lista de registros
        registros = empleadoControlador.obtenerEmpleados();
        indiceActual = 0;

        // Crea la interfaz gráfica
        frame = new JFrame("Vista Uno a Uno");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel central para los campos
        JPanel panelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panelCampos.add(new JLabel("Id:"));
        Id = new JTextField();
        panelCampos.add(Id);

        panelCampos.add(new JLabel("Nombre:"));
        nombre = new JTextField();
        panelCampos.add(nombre);

        panelCampos.add(new JLabel("Puesto:"));
        puesto = new JTextField();
        panelCampos.add(puesto);

        panelCampos.add(new JLabel("Salario:"));
        salario = new JTextField();
        panelCampos.add(salario);

        // Panel para mostrar la foto
        JPanel panelFoto = new JPanel(new BorderLayout());
        foto = new JLabel();
        foto.setHorizontalAlignment(SwingConstants.CENTER);
        panelFoto.setBorder(BorderFactory.createTitledBorder("Foto del empleado"));
        panelFoto.add(foto, BorderLayout.CENTER);
        frame.add(panelFoto, BorderLayout.EAST);

        // Panel inferior para los botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        btnAnterior = new JButton("Anterior");
        btnSiguiente = new JButton("Siguiente");
        btnPrimero = new JButton("Primero");
        btnUltimo = new JButton("Ultimo");
        btnVolver = new JButton("Volver");

        panelBotones.add(btnPrimero);
        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        panelBotones.add(btnUltimo);
        panelBotones.add(btnVolver);

        frame.add(panelCampos, BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.SOUTH);

        // Listeners para los botones
        btnAnterior.addActionListener(e -> {
            mostrarRegistroAnterior();
            actualizarEstadoBotones();
        });

        btnSiguiente.addActionListener(e -> {
            mostrarRegistroSiguiente();
            actualizarEstadoBotones();
        });
        btnVolver.addActionListener(e -> volverAlMainFrame());

        btnPrimero.addActionListener(e -> {
            indiceActual = 0;
            mostrarRegistro();
            actualizarEstadoBotones();
        });
        btnUltimo.addActionListener(e -> {
            indiceActual = registros.size() - 1;
            mostrarRegistro();
            actualizarEstadoBotones();
        });

        // Muestra el primer registro
        mostrarRegistro();
        actualizarEstadoBotones();

        // Muestra la ventana
        frame.setVisible(true);
    }

    private void mostrarRegistro() {
        if (registros.isEmpty()) return;

        empleados registroActual = registros.get(indiceActual);
        Id.setText(registroActual.getId().toString());
        nombre.setText(registroActual.getNombre());
        puesto.setText(registroActual.getPuesto());
        salario.setText(String.valueOf(registroActual.getSalario()));

        // Cargar y mostrar la foto
        cargarFoto(registroActual.getFoto());
    }

    private void cargarFoto(String numeroFoto) {
        // Ruta base donde se almacenan las fotos
        String rutaBase = "C:\\proyectos\\psp\\actividades\\act5psp\\src\\main\\resources\\fotos\\";

        // Construir la ruta completa para la foto del empleado
        String rutaCompleta = rutaBase + numeroFoto + ".jpg";

        // Ruta de la imagen por defecto
        String rutaDefault = rutaBase + "default.jpg";

        // Intentar cargar la imagen
        File archivoFoto = new File(rutaCompleta);
        if (archivoFoto.exists()) {
            // Si la imagen existe, cargarla
            ImageIcon icono = new ImageIcon(rutaCompleta);
            // Redimensionar la imagen para que se ajuste al JLabel
            Image imagenRedimensionada = icono.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            foto.setIcon(new ImageIcon(imagenRedimensionada));
        } else {
            // Si no existe la imagen, cargar la imagen por defecto
            File archivoDefault = new File(rutaDefault);
            if (archivoDefault.exists()) {
                ImageIcon iconoDefault = new ImageIcon(rutaDefault);
                Image imagenRedimensionada = iconoDefault.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
                foto.setIcon(new ImageIcon(imagenRedimensionada));
            } else {
                // Si no se encuentra la imagen por defecto, mostrar un mensaje de error en consola
                System.err.println("No se encuentra la imagen por defecto en: " + rutaDefault);
            }
        }
    }


    private void mostrarRegistroAnterior() {
        if (indiceActual > 0) {
            indiceActual--;
            mostrarRegistro();
        }
    }

    private void mostrarRegistroSiguiente() {
        if (indiceActual < registros.size() - 1) {
            indiceActual++;
            mostrarRegistro();
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
    private void actualizarEstadoBotones() {
        btnAnterior.setEnabled(indiceActual > 0);
        btnPrimero.setEnabled(indiceActual > 0);
        btnSiguiente.setEnabled(indiceActual < registros.size() - 1);
        btnUltimo.setEnabled(indiceActual < registros.size() - 1);
    }


}


