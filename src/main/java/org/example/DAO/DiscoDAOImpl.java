package org.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.DAO.interfaces.DiscoDAO;
import org.example.model.Disco;
import org.example.model.Cancion;

public class DiscoDAOImpl implements DiscoDAO {

    private static final String URL = "jdbc:h2:./data/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "discos";

    public DiscoDAOImpl() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, titulo VARCHAR(255), fecha_nac DATE, id_musico INT)";
            statement.executeUpdate(createTableQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Disco crearDisco(String titulo, Date fecha, int idMusico) {
        Disco disco = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(titulo, id_musico) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, titulo);
            preparedStatement.setInt(2, idMusico);
            preparedStatement.executeUpdate();



            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                disco = new Disco(titulo, fecha);
                disco.setId(id);
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disco;
    }

    @Override
    public List<Disco> obtenerDiscosConCanciones() {
        List<Disco> discos = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT * FROM " + TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");
                Date fecha = resultSet.getDate("fecha");
                Disco disco = new Disco(titulo, fecha);
                disco.setId(id);

                List<Cancion> canciones = obtenerCancionesPorDiscoId(id);
                disco.setCanciones(canciones);

                discos.add(disco);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discos;
    }

    private List<Cancion> obtenerCancionesPorDiscoId(int discoId) {
        List<Cancion> canciones = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String selectQuery = "SELECT * FROM canciones WHERE id_disco = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, discoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String genero = resultSet.getString("genero");
                String letra = resultSet.getString("letra");
                String descripcion = resultSet.getString("descripcion");

                Cancion cancion = new Cancion(nombre, genero, letra, descripcion);
                cancion.setId(id);

                canciones.add(cancion);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canciones;
    }
}
