package org.example.ui;

import org.example.DAO.PersonDAO;
import org.example.DAO.PersonDAOH2Impl;
import org.example.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SwingForm extends JFrame {

    private JTextField nameField;
    private JTextField surnameField;
    private JTextField ageField;

    public SwingForm() {
        setTitle("Swing Form");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(ageLabel);
        panel.add(ageField);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData();
            }
        });

        panel.add(sendButton);

        add(panel);

        JButton sendButton2 = new JButton("Send");
        sendButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData();
            }
        });
        panel.add(sendButton2);

        JButton listAllButton = new JButton("Listar Todos");
        listAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listAllPersons();
            }
        });
        panel.add(listAllButton);

        add(panel);
    }

    private void listAllPersons() {
        PersonDAO personDAO = new PersonDAOH2Impl();
        List<Person> allPersons = personDAO.getAllPersons();
        for (Person person : allPersons) {
            System.out.println(person);
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PersonsTableFrame tableFrame = new PersonsTableFrame(allPersons);
                tableFrame.setVisible(true);
            }
        });
    }


    private void sendData() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        int age = Integer.parseInt(ageField.getText());


        System.out.println("Name: " + name + ", Surname: " + surname + ", Age: " + age);

        //ACA

        PersonDAO personDAO = new PersonDAOH2Impl();
        Person person = new Person(name, surname, age);
        personDAO.savePerson(person);
        System.out.println(person.getId());
        System.out.println(personDAO.getAllPersons());
    }

    public static void main(String[] args) {
        SwingForm form = new SwingForm();
        form.setVisible(true);
    }
}
