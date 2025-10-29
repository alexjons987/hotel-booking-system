package models;

public class Room
{
    private int id;
    private boolean is_available;
    private double price;
    private String room_type;

    public Room(int id, boolean is_available, double price, String room_type) {
        this.id = id;
        this.is_available = is_available;
        this.price = price;
        this.room_type = room_type;
    }

    public int getId() {return id;}
    public boolean isIs_available() {return is_available;}
    public double getPrice() {return price;}
    public String getRoom_type() {return room_type;}
}
