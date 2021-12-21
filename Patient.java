package app;

public class Patient {
    private String firstname;
    private String lastname;
    private String phone;

    public Patient(String firstname, String lastname, String phone){
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getPhone(){
        return phone;
    }
}