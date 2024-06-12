package org.example.DAO.interfaces;

import org.example.model.Cancion;

public interface CancionDAO {
    Cancion crearCancion(String nombre, String genero, String letra, String descripcion);
    Cancion obtenerCancionPorId(int id);
    void actualizarCancion(Cancion cancion);
    void eliminarCancion(int id);
}