package modelo;

import java.sql.Date;

public class proyectos {
    private Integer id;
    private String nombre;
    private Date fecha_inicio;
    private Date fecha_termino;
    private Integer presupuesto;
    private Integer gasto_actual;

    public proyectos(Integer id, String nombre, Date fecha_inicio, Date fecha_termino, Integer presupuesto, Integer gasto_actual) {
        this.id = id;
        this.nombre = nombre;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
        this.presupuesto = presupuesto;
        this.gasto_actual = gasto_actual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGasto_actual() {
        return gasto_actual;
    }

    public void setGasto_actual(Integer gasto_actual) {
        this.gasto_actual = gasto_actual;
    }

    public Integer getPresupuesto() {
        return presupuesto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
