package org.example.DAO.interfaces;

import org.example.model.Disco;

import java.util.Date;
import java.util.List;

public interface DiscoDAO {
    public Disco crearDisco(String titulo, Date fecha, int idMusico);
    public List<Disco> obtenerDiscosConCanciones();
}
