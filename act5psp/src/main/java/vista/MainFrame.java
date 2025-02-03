package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class MainFrame extends JFrame {
    // Componentes principales
    private JMenuBar menuBar;
    private JPanel panelCentral; // Aquí se cargarán los paneles dinámicos

    public MainFrame() {

        // Configuración básica de la ventana
        setTitle("Aplicación Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Configuración del menú
        configurarMenu();

        // Panel central (donde se mostrarán los diferentes JPanel dinámicamente)
        panelCentral = new JPanel(new BorderLayout());
        add(panelCentral, BorderLayout.CENTER);
    }

    private void configurarMenu() {
        menuBar = new JMenuBar();

        // Menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemCerrarSesion = new JMenuItem("Cerrar Sesión");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Eventos del menú Archivo
        itemCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });

        menuArchivo.add(itemCerrarSesion);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

        // Menú Gestión
        JMenu menuGestion = new JMenu("Gestión");
        JMenuItem itemEmpleados = new JMenuItem("Empleados");
        JMenuItem itemProyectos = new JMenuItem("Proyectos");
        JMenuItem itemRegistroEmple = new JMenuItem("Registrar Empleado");
        JMenuItem itemEliminarEmple = new JMenuItem("Eliminar Empleado");
        JMenuItem itemCertificados = new JMenuItem("Tabla de Certificados");

        // Eventos del menú Gestión
        itemEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarPanelEmpleados();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        itemProyectos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarPanelProyectos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        itemRegistroEmple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPanelRegistroEmpleado();
            }
        });

        itemEliminarEmple.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarPanelEliminarEmpleado();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        itemCertificados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cargarPanelCertificados();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        menuGestion.add(itemEmpleados);
        menuGestion.add(itemProyectos);
        menuGestion.add(itemRegistroEmple);
        menuGestion.add(itemEliminarEmple);
        menuGestion.add(itemCertificados);

        // Menú Ayuda
        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de");

        // Evento para mostrar la información "Acerca de"
        itemAcercaDe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarAcercaDe();
            }
        });

        menuAyuda.add(itemAcercaDe);

        // Agregar menús a la barra de menú
        menuBar.add(menuArchivo);
        menuBar.add(menuGestion);
        menuBar.add(menuAyuda);

        // Establecer la barra de menú
        setJMenuBar(menuBar);
    }

    // Métodos para cargar diferentes paneles
    private void cargarPanelEmpleados() throws SQLException {
        SwingUtilities.invokeLater(() -> {
            try {
                new VistaIndividual();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }finally {
                dispose();
            }
        });
    }

    private void cargarPanelProyectos() throws SQLException {
        new VistaProyecto();
        dispose();
    }

    private void cargarPanelRegistroEmpleado() {

        new VistaRegistroEmpleado();
        dispose();
    }

    private void cargarPanelEliminarEmpleado() throws SQLException {
        SwingUtilities.invokeLater(() -> {
            try {
                new VistaEliminarEmpleados();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        dispose();
    }

    private void cargarPanelCertificados() throws SQLException {
        SwingUtilities.invokeLater(() -> {
            try {
                new VistaTablaCertificados();
                dispose();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void cambiarPanel(JPanel nuevoPanel) {
        // Elimina el panel anterior y agrega el nuevo
        panelCentral.removeAll();
        panelCentral.add(nuevoPanel, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // Método para mostrar la información "Acerca de"
    private void mostrarAcercaDe() {
        JOptionPane.showMessageDialog(this,
                "Aplicación de Gestión\nVersión 3.14159265358979323846\nAutor: Roberto Caliani Cuesta",
                "Acerca de",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para cerrar sesión
    private void cerrarSesion() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas cerrar sesión?", "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            new LoginFrame().setVisible(true);
            dispose(); // Cierra el MainFrame
        }
    }

    // Método para salir de la aplicación
    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que deseas salir de la aplicación?", "Salir", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}

