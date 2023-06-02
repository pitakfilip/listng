package sk.fmfi.listng.course.domain;

import java.io.Serializable;
import java.time.LocalTime;

/**
 * Trieda reprezentujúca vyučovaciu hodinu pre skupinu v danom kurze.
 * Vyučovanie je opakované nejaký deň (Pon.-Ned. teda 0-6 v číselnej interpretácii),
 * každý týždeň v určenom čase, v určenej miestnosti a trvá určitý počet minút.
 */
public class Class implements Serializable {

    private long id;

    private Room room;

    private long groupId;

    private int day;

    private LocalTime time;

    private int duration; // MINUTES

    @Deprecated
    public Class() {
        // Hibernate only
    }

    public Class(Room room, long groupId, int day, LocalTime time, int duration) {
        this.room = room;
        this.groupId = groupId;
        this.day = day;
        this.time = time;
        this.duration = duration;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
