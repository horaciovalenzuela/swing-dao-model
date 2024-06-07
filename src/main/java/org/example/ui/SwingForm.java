package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }

    private void sendData() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        int age = Integer.parseInt(ageField.getText());

        System.out.println("Name: " + name + ", Surname: " + surname + ", Age: " + age);
    }

    public static void main(String[] args) {
        SwingForm form = new SwingForm();
        form.setVisible(true);
    }
}
