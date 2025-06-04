package com.gmail.deniska1406sme.sceduleservice.dto;

import java.time.ZonedDateTime;

public class EventMessage {

    private Long id;
    private Long userId;
    private String title;
    private String description;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Integer notificationOffsetMinutes;

    public EventMessage() {
    }

    public EventMessage(Long id, Long userId, String title, String description, ZonedDateTime startTime, ZonedDateTime endTime, Integer notificationOffsetMinutes) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.notificationOffsetMinutes = notificationOffsetMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(ZonedDateTime startTime) {
        this.startTime = startTime;
    }

    public ZonedDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(ZonedDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getNotificationOffsetMinutes() {
        return notificationOffsetMinutes;
    }

    public void setNotificationOffsetMinutes(Integer notificationOffsetMinutes) {
        this.notificationOffsetMinutes = notificationOffsetMinutes;
    }
}
