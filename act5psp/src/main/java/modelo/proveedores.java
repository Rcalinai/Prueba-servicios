package modelo;

public class proveedores {
    private Integer id;
    private String nombre;
    private String especialidad;
    private Integer  id_proyecto;

    public proveedores(Integer id, String nombre, String especialidad, Integer id_proyecto) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.id_proyecto = id_proyecto;
    }
}
