package com.example.practica.practicaproject;

public class Bus {
    private int number;
    private String start_end;
    private String route;
    private String scheduleOnWeekday;
    private String scheduleOnWeekend;

    public Bus() {
    }

    public Bus(int number, String start_end, String route, String scheduleOnWeekday, String scheduleOnWeekend) {
        this.number = number;
        this.start_end = start_end;
        this.route = route;
        this.scheduleOnWeekday = scheduleOnWeekday;
        this.scheduleOnWeekend = scheduleOnWeekend;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStart_end() {
        return start_end;
    }

    public void setStart_end(String start_end) {
        this.start_end = start_end;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getScheduleOnWeekday() {
        return scheduleOnWeekday;
    }

    public void setScheduleOnWeekday(String scheduleOnWeekday) {
        this.scheduleOnWeekday = scheduleOnWeekday;
    }

    public String getScheduleOnWeekend() {
        return scheduleOnWeekend;
    }

    public void setScheduleOnWeekend(String scheduleOnWeekend) {
        this.scheduleOnWeekend = scheduleOnWeekend;
    }
}
