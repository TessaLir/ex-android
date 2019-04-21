package ru.vetukov.java.sb.homegps;

public class Item {

    private String Longetude;
    private String Latitude;

    public Item(String longetude, String latitude) {
        Longetude = longetude;
        Latitude = latitude;
    }

    public String getLongetude() {
        return Longetude;
    }

    public String getLatitude() {
        return Latitude;
    }
}
