package models;

import java.math.BigDecimal;

public class Room
{
    private final int id;
    private final boolean isAvailable;
    private final BigDecimal price;
    private final String room_type;

    public Room(int id, boolean is_available, BigDecimal price, String room_type) {
        this.id = id;
        this.isAvailable = is_available;
        this.price = price;
        this.room_type = room_type;
    }

    public int getId() {
        return id;
    }

    public boolean is_available() {
        return isAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getRoom_type() {
        return room_type;
    }


}
