package modelo;

import java.sql.Date;

public class certificaciones {
    private Integer id;
    private String nombre;
    private Date fecha_obtencion;
    private Integer id_empleado;

    public certificaciones(Integer id, Integer id_empleado, Date fecha_obtencion, String nombre) {
        this.id = id;
        this.id_empleado = id_empleado;
        this.fecha_obtencion = fecha_obtencion;
        this.nombre = nombre;
    }

    public Integer getId_empleado() {
        return id_empleado;
    }

    public Date getFecha_obtencion() {
        return fecha_obtencion;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }
}
