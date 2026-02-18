package com.priyanka.hostelmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String roomId;
    private String block;
    private String roomNumber;
    private int capacity;
    private int currentOccupancy;
    private List<String> occupants;
    private List<Review> reviews;
    private double averageRating;

    public Room() {
        this.occupants = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public Room(String roomId, String block, String roomNumber, int capacity) {
        this.roomId = roomId;
        this.block = block;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.currentOccupancy = 0;
        this.occupants = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.averageRating = 0.0;
    }

    public static class Review {
        private String userId;
        private String userName;
        private float rating;
        private String comment;
        private long timestamp;

        public Review() {}

        public Review(String userId, String userName, float rating, String comment) {
            this.userId = userId;
            this.userName = userName;
            this.rating = rating;
            this.comment = comment;
            this.timestamp = System.currentTimeMillis();
        }

        // Getters and Setters
        public String getUserId() { return userId; }
        public void setUserId(String userId) { this.userId = userId; }

        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }

        public float getRating() { return rating; }
        public void setRating(float rating) { this.rating = rating; }

        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }

        public long getTimestamp() { return timestamp; }
        public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    }

    // Getters and Setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }

    public String getBlock() { return block; }
    public void setBlock(String block) { this.block = block; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getCurrentOccupancy() { return currentOccupancy; }
    public void setCurrentOccupancy(int currentOccupancy) { this.currentOccupancy = currentOccupancy; }

    public List<String> getOccupants() { return occupants; }
    public void setOccupants(List<String> occupants) { this.occupants = occupants; }

    public List<Review> getReviews() { return reviews; }
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }

    public boolean isAvailable() {
        return currentOccupancy < capacity;
    }
}