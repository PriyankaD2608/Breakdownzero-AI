package com.priyanka.hostelmanagement.models;

import com.google.firebase.Timestamp;

public class MessMenu {
    private String date;
    private String breakfast;
    private String lunch;
    private String snacks;
    private String dinner;
    private Timestamp updatedAt;

    public MessMenu() {}

    public MessMenu(String date, String breakfast, String lunch, String snacks, String dinner) {
        this.date = date;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.snacks = snacks;
        this.dinner = dinner;
        this.updatedAt = Timestamp.now();
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getBreakfast() { return breakfast; }
    public void setBreakfast(String breakfast) { this.breakfast = breakfast; }

    public String getLunch() { return lunch; }
    public void setLunch(String lunch) { this.lunch = lunch; }

    public String getSnacks() { return snacks; }
    public void setSnacks(String snacks) { this.snacks = snacks; }

    public String getDinner() { return dinner; }
    public void setDinner(String dinner) { this.dinner = dinner; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}