package app;

public class Appointment {
    Patient p;
    private String date;
    private String hour;
    private String regDate;
    private String doctor;
    private Therapy t;

    public Appointment(Patient p, String date, String hour,
            String regDate, String doctor, Therapy t) {
        this.p = p;
        this.date = date;
        this.hour = hour;
        this.regDate = regDate;
        this.t = t;
        this.doctor = doctor;
    }

    public String getPFirstname() {
        return p.getFirstname();
    }

    public String getPLastname() {
        return p.getLastname();
    }

    public String getPhone() {
        return p.getPhone();
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    public String getRegdate() {
        return regDate;
    }

    public String getDoctorname() {
        return doctor;
    }

    public String getTName() {
        return t.getName();
    }

    public String getTPrice() {
        return t.getPrice();
    }

    public String getTDuration() {
        return t.getDuration();
    }
}