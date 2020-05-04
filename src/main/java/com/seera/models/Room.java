package com.seera.models;

import java.util.List;

public class Room {
    List<Guest> guests;

    public Room(List<Guest> guests) {
        this.guests = guests;
    }
}
