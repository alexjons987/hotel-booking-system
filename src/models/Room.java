package models;

import java.math.BigDecimal;

public class Room {
    private final int id;
    private final boolean isAvailable;
    private final BigDecimal price;
    private final String roomType;

    public Room(int id, boolean isAvailable, BigDecimal price, String roomType) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.price = price;
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getRoomType() {
        return roomType;
    }


}
