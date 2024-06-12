package org.example.DAO.interfaces;

import org.example.model.Musico;

import java.util.Date;
import java.util.List;

public interface MusicoDAO {
    Musico crearMusico(String nombre, String apellido, Date fechaNac);
    List<Musico> obtenerMusicosConDiscos();
    Musico obtenerMusicoPorId(int id);
    void actualizarMusico(Musico musico);
    void eliminarMusico(int id);
}
