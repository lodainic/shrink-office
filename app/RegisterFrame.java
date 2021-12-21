package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;

public class RegisterFrame {
    private JFrame f;
    private JLabel firstNameLabel, lastNameLabel,
            usernameLabel, passwLabel,
            emailLabel, funcLabel;
    private JTextField firstNameField, lastNameField,
            usernameField, passwField,
            emailField, funcField;
    private JButton registerButton;

    public RegisterFrame() {
        f = new JFrame();
        f.setTitle("Manage Therapies");
        JPanel panel = new JPanel();
        f.setSize(600, 450);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(panel);
        panel.setLayout(null);

        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setBounds(80, 70, 200, 30);
        panel.add(firstNameLabel);
        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setBounds(80, 110, 200, 30);
        panel.add(lastNameLabel);
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(80, 150, 200, 30);
        panel.add(usernameLabel);
        passwLabel = new JLabel("Password");
        passwLabel.setBounds(80, 190, 200, 30);
        panel.add(passwLabel);
        emailLabel = new JLabel("E-mail");
        emailLabel.setBounds(80, 230, 200, 30);
        panel.add(emailLabel);
        funcLabel = new JLabel("Function");
        funcLabel.setBounds(80, 270, 200, 30);
        panel.add(funcLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(300, 70, 200, 30);
        panel.add(firstNameField);
        lastNameField = new JTextField();
        lastNameField.setBounds(300, 110, 200, 30);
        panel.add(lastNameField);
        usernameField = new JTextField();
        usernameField.setBounds(300, 150, 200, 30);
        panel.add(usernameField);
        passwField = new JPasswordField();
        passwField.setBounds(300, 190, 200, 30);
        panel.add(passwField);
        emailField = new JTextField();
        emailField.setBounds(300, 230, 200, 30);
        panel.add(emailField);
        funcField = new JTextField("doctor/assistant");
        funcField.setBounds(300, 270, 200, 30);
        panel.add(funcField);
        registerButton = new JButton("Register");
        registerButton.setBounds(360, 350, 100, 30);
        registerButton.addActionListener(new rfListener());
        panel.add(registerButton);
        f.setVisible(true);
    }

    public boolean completedFields() {
        if (firstNameField.getText().equals("") || lastNameField.getText().equals("") ||
                emailField.getText().equals("") || funcField.getText().equals("") ||
                usernameField.getText().equals("") || passwField.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private class rfListener implements ActionListener {
        private UsersCollection usrsColl = new UsersCollection();

        private rfListener() {
            usrsColl = UsersCollection.getInstance();
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerButton && completedFields()) {
                try {
                    if (!usrsColl.existUsername(usernameField.getText())) {
                        try {
                            usrsColl.saveCredentials(usernameField.getText(),
                                    passwField.getText());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            usrsColl.savePersonalData(firstNameField.getText(),
                                    lastNameField.getText(),
                                    emailField.getText(),
                                    funcField.getText(),
                                    usernameField.getText());
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }

                        f.dispose();
                        new LogInFrame();
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "The username is taken!");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Fill in all fields!");
            }
        }
    }
}
