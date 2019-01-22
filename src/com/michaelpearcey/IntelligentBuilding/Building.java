package com.michaelpearcey.IntelligentBuilding;

import java.util.ArrayList;

public class Building extends ArrayList<Room> {

    public Building() {}

    public void addRoom(Room r) { add(r); }

    public void addRooms(ArrayList<Room> r) { addAll(r); }
}
