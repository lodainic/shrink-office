package app;

public class Therapy {
    private String name;
    private String price;
    private String duration;

    public Therapy(String name, String price, String duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDuration() {
        return duration;
    }
}