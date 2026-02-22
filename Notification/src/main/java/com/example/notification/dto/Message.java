package com.example.notification.dto;

public class Message {
    private long eventId;
    private long userId;

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    // TODO: override equals and hashcode
}

