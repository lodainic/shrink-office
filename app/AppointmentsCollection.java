package app;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentsCollection {
    private ManageDatabase mn = new ManageDatabase();
    private static AppointmentsCollection instance;

    public AppointmentsCollection() {
        // Constructor
    }
    public void saveAppointment(Appointment a) throws SQLException {
        String sql = "INSERT INTO appointments(pfname,plname,date,"
                    + "hour,regdate,phone,doctorname,therapy_id) VALUES('"
                    +a.getPFirstname()+"','"
                    +a.getPLastname()+"','"+a.getDate()+"','"
                    +a.getHour()+"','"+a.getRegdate()+"','"
                    +a.getPhone()+"','"+a.getDoctorname()
                    +"',(SELECT therapy_id FROM therapies WHERE name = '"
                    +a.getTName()+"'));";
        mn.openDB();
        mn.executeU(sql);
        mn.closeDB();
    }
    public void deleteAppointment(Appointment a) throws SQLException {
        String sql = "DELETE FROM appointments WHERE pfname IN ('"
                    +a.getPFirstname()+"') AND plname IN ('"
                    +a.getPLastname()+"') AND date IN ('"
                    +a.getDate()+"') AND hour IN ('"
                    +a.getHour()+"');";
        mn.openDB();
        mn.executeU(sql);
        mn.closeDB();
    }
    public String[][] getAppointments() throws SQLException{
        String sql = "SELECT appointments.pfname as fname, appointments.plname as lname,"
                    +" appointments.phone as phone, appointments.date as date, appointments.hour as hour,"
                    +" therapies.name as tname, therapies.price as price, appointments.doctorname as dname"
                    +" FROM appointments INNER JOIN therapies ON"
                    +" appointments.therapy_id = therapies.therapy_id;";
        mn.openDB();
        ResultSet rs = mn.executeQ(sql);
        String[][] data = new String[20][8];
        int i = 0, j;
        
        while(rs.next()){
            j = 0;
            data[i][j] = rs.getString("fname");
            j++;
            data[i][j] = rs.getString("lname");
            j++;
            data[i][j] = rs.getString("phone");
            j++;
            data[i][j] = rs.getString("date");
            j++;
            data[i][j] = rs.getString("hour");
            j++;
            data[i][j] = rs.getString("tname");
            j++;
            data[i][j] = rs.getString("price");
            j++;
            data[i][j] = rs.getString("dname");
            i++;
        }
    
        mn.closeDB();
        return data;
    }
    public static AppointmentsCollection getInstance() {
        if (instance == null)
            instance = new AppointmentsCollection();
        return instance;
    }
}
