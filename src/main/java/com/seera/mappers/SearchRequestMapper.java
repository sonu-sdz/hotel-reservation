package com.seera.mappers;

import com.seera.models.Room;

import java.util.List;

public class SearchRequestMapper {
    private Dates dates;
    private String destination;
    private List<Room> room;
    private String placeId;

    class Dates {
        private String checkin;
        private String checkout;

        Dates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }
    }
}
