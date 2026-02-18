package com.priyanka.hostelmanagement.models;

public class User {
    private String userId;
    private String name;
    private String email;
    private String rollNumber;
    private int year;
    private String roomNumber;
    private String block;
    private String phoneNumber;
    private String role;
    private String fcmToken;

    public User() {}

    public User(String userId, String name, String email, String rollNumber,
                int year, String roomNumber, String block, String phoneNumber, String role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.rollNumber = rollNumber;
        this.year = year;
        this.roomNumber = roomNumber;
        this.block = block;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getBlock() { return block; }
    public void setBlock(String block) { this.block = block; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getFcmToken() { return fcmToken; }
    public void setFcmToken(String fcmToken) { this.fcmToken = fcmToken; }
}