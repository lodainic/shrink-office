package app;

public class User {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String function;
    private String email;

    public User(String firstname, String lastname,
            String username, String password,
            String function, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.function = function;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
