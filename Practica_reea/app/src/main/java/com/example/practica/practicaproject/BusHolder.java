package com.example.practica.practicaproject;

import java.util.ArrayList;
import java.util.List;

public class BusHolder {
    private List<Bus> buses;

    BusHolder() {

    }

    BusHolder(String[] number, String[] start_end, String[] desc) {
        buses = new ArrayList<>();
        for (int i = 0; i < number.length; i++) {
            buses.add(new Bus(Integer.parseInt(number[i]), start_end[i], desc[i], null, null));
        }
    }

    public String[] getBusNumbers() {
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < buses.size(); i++) {
            tmp.add(Integer.toString(buses.get(i).getNumber()));
        }
        return tmp.toArray(new String[0]);
    }

    public String[] getBusStartEnds() {
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < buses.size(); i++) {
            tmp.add(buses.get(i).getStart_end());
        }
        return tmp.toArray(new String[0]);
    }

    public String[] getBusRoutes() {
        List<String> tmp = new ArrayList<>();
        for (int i = 0; i < buses.size(); i++) {
            tmp.add(buses.get(i).getRoute());
        }
        return tmp.toArray(new String[0]);
    }
}
