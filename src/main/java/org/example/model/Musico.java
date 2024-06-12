package org.example.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Musico {

    private int id;
    private Date fechaNac;
    private String nombre, apellido;
    private List<Disco> discos;

    public Musico(String nombre, String apellido, Date fechaNac) {
        this.fechaNac = fechaNac;
        this.nombre = nombre;
        this.apellido = apellido;
        this.discos = new ArrayList<>();
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Disco> getDiscos() {
        return discos;
    }

    public void setDiscos(List<Disco> discos) {
        this.discos = discos;
    }

    public void agregarDisco(Disco disco) {
        discos.add(disco);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
