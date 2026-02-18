package com.priyanka.hostelmanagement.models;

import com.google.firebase.Timestamp;

public class Activity {
    private String activityId;
    private String title;
    private String description;
    private Timestamp date;
    private String time;
    private String venue;
    private String imageUrl;

    public Activity() {}

    public Activity(String activityId, String title, String description,
                    Timestamp date, String time, String venue) {
        this.activityId = activityId;
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.venue = venue;
    }

    public String getActivityId() { return activityId; }
    public void setActivityId(String activityId) { this.activityId = activityId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Timestamp getDate() { return date; }
    public void setDate(Timestamp date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getVenue() { return venue; }
    public void setVenue(String venue) { this.venue = venue; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}