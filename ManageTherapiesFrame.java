package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ManageTherapiesFrame {
    private JFrame f;
    private JLabel therapiesLabel, nameLabel, priceLabel, durationLabel;
    private JButton deleteButton, saveButton;
    private JTextField nameTextField, priceTextField, durationTextField;
    private JComboBox therapiesComboBox;
    private Object[] therapies;

    public ManageTherapiesFrame() throws SQLException {
        f = new JFrame();
        f.setTitle("Manage Therapies");
        JPanel panel = new JPanel();
        f.setSize(650, 300);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.add(panel);
        panel.setLayout(null);

        TherapiesCollection ther = new TherapiesCollection();
        therapies = ther.getTherapies().toArray();

        therapiesLabel = new JLabel("Therapies");
        panel.add(therapiesLabel);
        therapiesLabel.setBounds(50, 30, 100, 30);
        therapiesComboBox = new JComboBox<>(therapies);
        therapiesComboBox.setBounds(150, 30, 250, 30);
        therapiesComboBox.addActionListener(new MtfListener());
        panel.add(therapiesComboBox);
        deleteButton = new JButton("Delete");
        deleteButton.setBounds(450, 30, 145, 30);
        deleteButton.addActionListener(new MtfListener());
        panel.add(deleteButton);
        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 70, 100, 30);
        panel.add(nameLabel);
        nameTextField = new JTextField();
        nameTextField.setBounds(150, 70, 250, 30);
        panel.add(nameTextField);
        priceLabel = new JLabel("Price");
        priceLabel.setBounds(50, 110, 100, 30);
        panel.add(priceLabel);
        priceTextField = new JTextField();
        priceTextField.setBounds(150, 110, 250, 30);
        panel.add(priceTextField);
        durationLabel = new JLabel("Duration");
        durationLabel.setBounds(50, 150, 100, 30);
        panel.add(durationLabel);
        durationTextField = new JTextField();
        durationTextField.setBounds(150, 150, 250, 30);
        panel.add(durationTextField);
        saveButton = new JButton("Save");
        saveButton.setBounds(450, 150, 145, 30);
        saveButton.addActionListener(new MtfListener());
        panel.add(saveButton);
        f.setVisible(true);
    }

    public boolean completedFields() {
        if (nameTextField.getText().equals("") ||
                priceTextField.getText().equals("") ||
                durationTextField.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private class MtfListener implements ActionListener {
        private TherapiesCollection tc;

        private MtfListener() {
            tc = TherapiesCollection.getInstance();
        }

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveButton && completedFields()) {
                Therapy th = new Therapy(nameTextField.getText(),
                        priceTextField.getText(),
                        durationTextField.getText());
                try {
                    tc.saveTherapy(th);
                    therapiesComboBox.addItem(nameTextField.getText()
                            + "," + priceTextField.getText()
                            + "," + durationTextField.getText());
                    nameTextField.setText("");
                    priceTextField.setText("");
                    durationTextField.setText("");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Could not save the therapy!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                f.dispose();
                try {
                    new AppointmentsFrame();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Fill in all text fields!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (e.getSource() == deleteButton) {
                try {
                    tc.deleteTherapy(((String) therapiesComboBox.getSelectedItem()));
                    therapiesComboBox.removeItem(((String) therapiesComboBox.getSelectedItem()));
                    f.dispose();
                    new AppointmentsFrame();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Could not delete the therapy!", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}