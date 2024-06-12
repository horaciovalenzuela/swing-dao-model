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

import org.example.DAO.interfaces.MusicoDAO;
import org.example.model.Musico;
import org.example.model.Disco;

public class MusicoDAOImpl implements MusicoDAO {

    private static final String URL = "jdbc:h2:./data/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";
    private static final String TABLE_NAME = "musicos";

    public MusicoDAOImpl() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                    "(id INT AUTO_INCREMENT PRIMARY KEY, nombre VARCHAR(255), apellido VARCHAR(255), fecha_nac DATE)";
            statement.executeUpdate(createTableQuery);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Musico crearMusico(String nombre, String apellido, Date fechaNac) {
        Musico musico = null;
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String insertQuery = "INSERT INTO " + TABLE_NAME + "(nombre, apellido, fecha_nac) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setDate(3, new java.sql.Date(fechaNac.getTime()));
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                musico = new Musico(nombre, apellido, fechaNac);
                musico.setId(id);
            }

            preparedStatement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return musico;
    }

    @Override
    public List<Musico> obtenerMusicosConDiscos() {
        List<Musico> musicos = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            String selectQuery = "SELECT * FROM " + TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(selectQuery);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                Date fechaNac = resultSet.getDate("fecha_nac");

                Musico musico = new Musico(nombre, apellido, fechaNac);
                musico.setId(id);

                // Obtener discos del músico
                List<Disco> discos = obtenerDiscosPorMusicoId(id);
                musico.setDiscos(discos);

                musicos.add(musico);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return musicos;
    }

    private List<Disco> obtenerDiscosPorMusicoId(int musicoId) {
        List<Disco> discos = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            String selectQuery = "SELECT * FROM discos WHERE id_musico = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, musicoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String titulo = resultSet.getString("titulo");

                Disco disco = new Disco(titulo);
                disco.setId(id);

                discos.add(disco);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discos;
    }

}

