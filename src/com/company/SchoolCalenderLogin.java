package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class SchoolCalendarLogin extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    public SchoolCalendarLogin() {
        // Set up the JFrame
        setTitle("School Calendar Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create the components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        signUpButton = new JButton("Sign Up");

        // Create a panel and set the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Add components to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(loginButton);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(signUpButton);

        // Add the panel to the frame
        add(panel);

        // Attach action listeners to the buttons
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Add your login validation logic here
                // For simplicity, we will just display a message dialog
                if (isValidLogin(username, password)) {
                    JOptionPane.showMessageDialog(SchoolCalendarLogin.this, "Login successful!");
                    // Open the school calendar window here
                } else {
                    JOptionPane.showMessageDialog(SchoolCalendarLogin.this, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Add your sign-up logic here
                if (registerUser(username, password)) {
                    JOptionPane.showMessageDialog(SchoolCalendarLogin.this, "Sign-up successful! You can now login.");
                } else {
                    JOptionPane.showMessageDialog(SchoolCalendarLogin.this, "Sign-up failed! Please try a different username.", "Sign-up Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean isValidLogin(String username, String password) {
        // Add your login validation logic here
        // For simplicity, we will use a file to store registered usernames and passwords

        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String storedUsername = parts[0];
                String storedPassword = parts[1];
                if (username.equals(storedUsername) && password.equals(storedPassword)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean registerUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
