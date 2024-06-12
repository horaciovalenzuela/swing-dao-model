package org.example.DAO;

import org.example.DAO.interfaces.CancionDAO;
import org.example.model.Cancion;

public class CancionDAOImpl implements CancionDAO {
    @Override
    public Cancion crearCancion(String nombre, String genero, String letra, String descripcion) {
        return null;
    }

    @Override
    public Cancion obtenerCancionPorId(int id) {
        return null;
    }

    @Override
    public void actualizarCancion(Cancion cancion) {

    }

    @Override
    public void eliminarCancion(int id) {

    }
}
