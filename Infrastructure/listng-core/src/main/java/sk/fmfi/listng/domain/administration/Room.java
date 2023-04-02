package sk.fmfi.listng.domain.administration;

/**
 * Trieda reprezentujúca fyzickú vyučovaciu miestnosť.
 */

public class Room {
    
    private long id;

    private String name;

    private int capacity;

    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
