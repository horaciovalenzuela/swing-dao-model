package org.example.ui;

import org.example.model.Person;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PersonsTableFrame extends JFrame {

    public PersonsTableFrame(List<Person> persons) {
        setTitle("Listado de Personas");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Surname");
        tableModel.addColumn("Age");

        for (Person person : persons) {
            Object[] row = {person.getId(), person.getName(), person.getSurname(), person.getAge()};
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        setContentPane(panel);

        setVisible(true);
    }
}
