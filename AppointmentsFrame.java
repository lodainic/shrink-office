package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class AppointmentsFrame {
    private JFrame f;
    private JButton addButton;
    private JButton deleteButton;
    private JButton logOutButton;
    private JButton therapiesButton;
    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private JTable table;

    public AppointmentsFrame() throws SQLException {
        f = new JFrame();
        f.setTitle("Appointments");
        JPanel panel = new JPanel();
        f.setSize(900, 500);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.add(panel, BorderLayout.SOUTH);
        addButton = new JButton("Add");
        addButton.addActionListener(new AFListener());
        panel.add(addButton);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new AFListener());
        panel.add(deleteButton);
        therapiesButton = new JButton("Therapies");
        therapiesButton.addActionListener(new AFListener());
        panel.add(therapiesButton);

        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(new AFListener());
        panel.add(logOutButton);
        String[] columnNames = { "First Name", "Last Name", "Phone",
                "Date", "Hour", "Therapy", "Price",
                "Doctor" };
        String[][] data = getData();
        tableModel = new DefaultTableModel(data, columnNames);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        table.setAutoCreateRowSorter(true);
        f.add(scrollPane, BorderLayout.CENTER);
        f.setVisible(true);
    }

    public String[][] getData() throws SQLException {
        AppointmentsCollection ac = new AppointmentsCollection();
        return ac.getAppointments();
    }

    private class AFListener implements ActionListener {
        private AppointmentsCollection ac;

        private AFListener() {
            ac = AppointmentsCollection.getInstance();
        }

        public void actionPerformed(ActionEvent e) {
            int r;
            if (e.getSource() == addButton) {
                f.dispose();
                try {
                    new AddAppointmentFrame();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (e.getSource() == deleteButton) {
                r = table.getSelectedRow();
                tableModel.removeRow(r);
                Patient p = new Patient((String) table.getValueAt(r, 0),
                        (String) table.getValueAt(r, 1),
                        (String) table.getValueAt(r, 2));
                Appointment a = new Appointment(p,
                        (String) table.getValueAt(r, 3),
                        (String) table.getValueAt(r, 4),
                        "", "",
                        new Therapy("", "", ""));
                try {
                    ac.deleteAppointment(a);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (e.getSource() == therapiesButton) {
                f.dispose();
                try {
                    new ManageTherapiesFrame();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            if (e.getSource() == logOutButton) {
                f.dispose();
                new LogInFrame();
            }
        }
    }
}