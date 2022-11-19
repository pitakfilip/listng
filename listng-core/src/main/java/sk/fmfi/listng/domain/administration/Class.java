package sk.fmfi.listng.domain.administration;

import java.time.LocalTime;

/**
 * Trieda reprezentujúca vyučovaciu hodinu pre skupinu v danom kurze.
 * Vyučovanie je opakované nejaký deň (Pon.-Ned. teda 0-6 v číselnej interpretácii),
 * každý týždeň v určenom čase a v určenej miestnosti.
 */

public class Class {

    private Room room;

    private Group group;

    private int day;

    private LocalTime time;

    private Class (Room room, Group group, int day, String time) {
        this.room = room;
        this.group = group;
        this.day = day;
        this.time = LocalTime.parse(time);
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
