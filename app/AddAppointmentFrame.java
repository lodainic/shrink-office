package app;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class AddAppointmentFrame {
    private JFrame f = new JFrame();
    private JLabel firstNameLabel, lastNameLabel,
            phoneLabel, hourLabel,
            doctorLabel, dateLabel,
            therapyLabel;
    private JComboBox dayComboBox, monthComboBox,
            yearComboBox, hourComboBox,
            doctorComboBox, therapyComboBox;
    private JTextField firstNameField, lastNameField, phoneField;
    private JButton saveButton;
    private String[] dates = { "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
            "17", "18", "19", "20", "21", "22", "23", "24",
            "25", "26", "27", "28", "29", "30", "31" };
    private String[] months = { "01", "02", "03", "04", "05", "06",
            "07", "08", "09", "10", "11", "12" };
    private String[] years = { "2021", "2022", "2023", "2024",
            "2025", "2026", "2027" };
    private String[] hours = { "8:00", "9:00", "10:00", "11:00",
            "12:00", "13:00", "14:00", "15:00", "16:00" };
    private Object[] therNames;
    private Object[] doctors;

    public AddAppointmentFrame() throws SQLException {
        TherapiesCollection tc = new TherapiesCollection();
        therNames = tc.getTherapies().toArray();
        UsersCollection uc = new UsersCollection();
        doctors = uc.getDoctorsNames().toArray();
        f.setTitle("Create Apointment");
        JPanel panel = new JPanel();
        f.setSize(600, 600);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.add(panel);
        panel.setLayout(null);
        firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(50, 100, 150, 30);
        panel.add(firstNameLabel);
        lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(50, 150, 150, 30);
        panel.add(lastNameLabel);
        phoneLabel = new JLabel("Phone:");
        panel.add(phoneLabel);
        phoneLabel.setBounds(50, 200, 150, 30);
        therapyLabel = new JLabel("Therapy:");
        therapyLabel.setBounds(50, 400, 100, 30);
        panel.add(therapyLabel);
        doctorLabel = new JLabel("Doctor:");
        doctorLabel.setBounds(50, 250, 250, 30);
        panel.add(doctorLabel);
        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 300, 200, 30);
        panel.add(dateLabel);
        hourLabel = new JLabel("Hour");
        hourLabel.setBounds(50, 350, 200, 30);
        panel.add(hourLabel);
        firstNameField = new JTextField();
        firstNameField.setBounds(200, 100, 275, 30);
        panel.add(firstNameField);
        lastNameField = new JTextField();
        lastNameField.setBounds(200, 150, 275, 30);
        panel.add(lastNameField);
        phoneField = new JTextField();
        phoneField.setBounds(200, 200, 275, 30);
        panel.add(phoneField);
        therapyComboBox = new JComboBox<>(therNames);
        therapyComboBox.setBounds(200, 400, 275, 30);
        therapyComboBox.addActionListener(new AafListener());
        panel.add(therapyComboBox);
        doctorComboBox = new JComboBox<>(doctors);
        doctorComboBox.setBounds(200, 250, 275, 30);
        panel.add(doctorComboBox);
        dayComboBox = new JComboBox<>(dates);
        dayComboBox.setBounds(200, 300, 60, 30);
        panel.add(dayComboBox);
        monthComboBox = new JComboBox<>(months);
        monthComboBox.setBounds(260, 300, 60, 30);
        panel.add(monthComboBox);
        yearComboBox = new JComboBox<>(years);
        yearComboBox.setBounds(320, 300, 60, 30);
        panel.add(yearComboBox);
        hourComboBox = new JComboBox<>(hours);
        hourComboBox.setBounds(200, 350, 70, 30);
        panel.add(hourComboBox);
        saveButton = new JButton("Save");
        saveButton.setBounds(200, 500, 130, 30);
        saveButton.addActionListener(new AafListener());
        panel.add(saveButton);
        f.setVisible(true);
    }

    public boolean completedFields() {
        if (firstNameField.getText().equals("") ||
                lastNameField.getText().equals("") ||
                phoneField.getText().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    private class AafListener implements ActionListener {
        private AppointmentsCollection apptntColln;

        private AafListener() {
            apptntColln = AppointmentsCollection.getInstance();
        }

        public void actionPerformed(ActionEvent e) {
            Patient p = new Patient(firstNameField.getText(),
                    lastNameField.getText(),
                    phoneField.getText());
            String[] ther = ((String) therapyComboBox.getSelectedItem()).split(", ");
            Therapy t = new Therapy(ther[0], ther[1], ther[2]);
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String regDate = dateFormat.format(date);
            String apDate = ((String) yearComboBox.getSelectedItem()) + "-" +
                    ((String) monthComboBox.getSelectedItem()) + "-" +
                    ((String) dayComboBox.getSelectedItem());
            Appointment a = new Appointment(p, apDate, ((String) hourComboBox.getSelectedItem()),
                    regDate, ((String) doctorComboBox.getSelectedItem()), t);
            if (e.getSource() == saveButton && completedFields()) {
                try {
                    apptntColln.saveAppointment(a);
                    f.dispose();
                    new AppointmentsFrame();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Fill in all text fields!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
