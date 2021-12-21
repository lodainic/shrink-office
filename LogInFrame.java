package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class LogInFrame{
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signinButton, signupButton;
    private JFrame f;
    public LogInFrame() {
        f = new JFrame();
        f.setTitle("Enter Acount");
        JPanel panel = new JPanel();
        f.setSize(450, 300);
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(panel);
        panel.setLayout(null);
        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(80, 70, 200, 30);
        panel.add(usernameLabel);
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(80, 110, 200, 30);
        panel.add(passwordLabel);
        usernameField = new JTextField();
        usernameField.setBounds(170, 70, 200, 30);
        panel.add(usernameField);
        passwordField = new JPasswordField();
        passwordField.setBounds(170, 110, 200, 30);
        panel.add(passwordField);
        signinButton = new JButton("Sign IN");
        signinButton.setBounds(110, 150, 100, 30);
        signinButton.addActionListener(new LfListener());
        panel.add(signinButton);
        signupButton = new JButton("Sign UP");
        signupButton.setBounds(250, 150, 100, 30);
        signupButton.addActionListener(new LfListener());
        panel.add(signupButton);
        f.setVisible(true);
    }

    private class LfListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            UsersCollection usrColln = new UsersCollection();
            try {
                if (e.getSource() == signinButton){
                    if (usrColln.checkCredentialsMatch(usernameField.getText(), passwordField.getText())==1) {
                        f.dispose();
                        new AppointmentsFrame();
                    }else {
                        JOptionPane.showMessageDialog(null, "Wrong Password");
                    }
                } 
                if (e.getSource() == signupButton) {
                    f.dispose();
                    new RegisterFrame();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}