package sk.fmfi.listng.domain.administration;

import sk.fmfi.listng.domain.user.User;

import java.sql.Timestamp;

public class Log {

    private Timestamp created;

    private User user;

    private String ipAddress;

    private String action;

    public Log(Timestamp created, User user, String ipAddress, String action) {
        this.created = created;
        this.user = user;
        this.ipAddress = ipAddress;
        this.action = action;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
