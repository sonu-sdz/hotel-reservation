package com.seera.mappers;

import com.seera.models.Dates;
import com.seera.models.Room;

import java.util.List;

public class SearchRequestMapper {
    private Dates dates;
    private String destination;
    private List<Room> room;
    private String placeId;

    public SearchRequestMapper(Dates dates, String destination, List<Room> room, String placeId) {
        this.dates = dates;
        this.destination = destination;
        this.room = room;
        this.placeId = placeId;
    }


}
