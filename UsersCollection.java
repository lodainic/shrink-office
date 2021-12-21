package app;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersCollection {
    private ManageDatabase mn = new ManageDatabase();
    private Secure s = new Secure();
    private static UsersCollection instance;

    public UsersCollection() {
        // Constructor
    }

    public boolean existUsername(String username) throws SQLException {
        String sql = "SELECT username FROM accounts WHERE username = '" + username + "';";
        mn.openDB();
        ResultSet rs = mn.executeQ(sql);

        if (!rs.isBeforeFirst()) {
            mn.closeDB();
            return false;
        } else {
            mn.closeDB();
            return true;
        }
    }

    public void saveCredentials(String username, String password)
            throws SQLException {

        String hashedPassword = s.secure(password);
        String sql = "INSERT INTO accounts(username,password) VALUES('"
                + username + "','" + hashedPassword + "');";
        mn.openDB();
        mn.executeU(sql);
        mn.closeDB();
    }

    public void savePersonalData(String firstName, String lastName, String email,
            String function, String username)
            throws SQLException {
        String sql = "INSERT INTO users(firstname,lastname,email,functn,account_id) VALUES('"
                + firstName + "','" + lastName + "','" + email + "','" + function
                + "',(SELECT account_id FROM accounts WHERE username = '" + username + "'));";
        mn.openDB();
        mn.executeU(sql);
        mn.closeDB();
    }

    public int checkCredentialsMatch(String username, String password) throws SQLException {
        String sql = "SELECT username, password FROM accounts;";
        mn.openDB();
        ResultSet rs = mn.executeQ(sql);
        int flag = 0;
        while (rs.next()) {
            String usern = rs.getString("username");
            String hashedp = rs.getString("password");
            boolean match = usern.equals(username);
            boolean match1 = s.verify(password, hashedp);
            if (match && match1) {
                flag += 1;
            }
        }
        mn.closeDB();
        return flag;
    }

    public List<String> getDoctorsNames() throws SQLException {
        String sql = "SELECT firstname, lastname FROM users WHERE functn IN ('doctor');";
        List<String> doctorsNames = new ArrayList<>();
        mn.openDB();
        ResultSet rs = mn.executeQ(sql);
        while (rs.next()) {
            doctorsNames.add(rs.getString("firstname") + " " + rs.getString("lastname"));
        }
        mn.closeDB();
        return doctorsNames;
    }

    public static UsersCollection getInstance() {
        if (instance == null) {
            instance = new UsersCollection();
        }
        return instance;
    }
}