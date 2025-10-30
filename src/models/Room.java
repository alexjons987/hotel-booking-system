package models;

import java.math.BigDecimal;

public class Room
{
    private final int id;
    private boolean is_available;
    private BigDecimal price;
    private String room_type;

    public Room(int id, boolean is_available, BigDecimal price, String room_type) {
        this.id = id;
        this.is_available = is_available;
        this.price = price;
        this.room_type = room_type;
    }

    public int getId() {return id;}
    public boolean is_available() {return is_available;}
    public BigDecimal getPrice() {return price;}
    public String getRoom_type() {return room_type;}

     //public void setId(int id) {this.id = id;}    should never be changed clientside
    public void setIs_available(boolean is_available) {this.is_available = is_available;}
    public void setPrice(BigDecimal price) {this.price = price;}
    public void setRoom_type(String room_type) {this.room_type = room_type;}
}
