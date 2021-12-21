package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TherapiesCollection {
    private ManageDatabase mn = new ManageDatabase();
    private static TherapiesCollection instance;

    public TherapiesCollection() {
        // Constructor
    }

    public void saveTherapy(Therapy th) throws SQLException {
        String sql = "INSERT INTO therapies(name,price,duration) VALUES ('"
                + th.getName() + "','" + th.getPrice() + "','" + th.getDuration() + "');";
        mn.openDB();
        mn.executeU(sql);
        mn.closeDB();
    }

    public void deleteTherapy(String th) throws SQLException {
        String[] ther = th.split(",");
        String sql = "DELETE FROM therapies WHERE name = '" + ther[0] + "';";
        mn.openDB();
        mn.executeU(sql);
        mn.closeDB();
    }

    public List<String> getTherapies() throws SQLException {
        List<String> therapies = new ArrayList<>();
        mn.openDB();
        ResultSet rs = mn.executeQ("SELECT name, price, duration FROM therapies;");
        while (rs.next()) {
            therapies.add(rs.getString("name") + ", " + rs.getString("price")
                    + ", " + rs.getString("duration"));
        }
        mn.closeDB();
        return therapies;
    }

    public String getTherapieDuration(String name) throws SQLException {
        mn.openDB();
        ResultSet rs = null;
        try {
            rs = mn.executeQ("SELECT duration FROM therapies WHERE name = '" + name + "';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mn.closeDB();
        return rs.getString("duration");
    }

    public static TherapiesCollection getInstance() {
        if (instance == null) {
            instance = new TherapiesCollection();
        }
        return instance;
    }
}