package org.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Disco {
    private int id;
    private String nombre;
    private Date fecha;


    private List<Cancion> canciones;

    public Disco(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.canciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<Cancion> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Cancion> canciones) {
        this.canciones = canciones;
    }

    public void agregarcANCION(Cancion cancion) {
        canciones.add(cancion);
    }
}
