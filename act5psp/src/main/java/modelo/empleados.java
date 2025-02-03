package modelo;

public class empleados {
    private Integer id;
    private String nombre;
    private String puesto;
    private String salario;
    private Integer id_proyecto;
    private String contrasenia;
    private Integer foto;
    static empleados empleado_actual = null;

    public empleados(Integer id, Integer foto, String contrasenia, Integer id_proyecto, String salario, String puesto, String nombre) {
        this.id = id;
        this.foto = foto;
        this.contrasenia = contrasenia;
        this.id_proyecto = id_proyecto;
        this.salario = salario;
        this.puesto = puesto;
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto.toString();
    }
    public Integer getFotoInt() {
        return foto;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public Integer getId_proyecto() {
        return id_proyecto;
    }

    public String getSalario() {
        return salario;
    }

    public String getPuesto() {
        return puesto;
    }
    public static empleados getEmpleado_actual() {
        return empleado_actual;
    }

    public static void setEmpleado_actual(empleados empleado_actual) {
        empleados.empleado_actual = empleado_actual;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }
}
