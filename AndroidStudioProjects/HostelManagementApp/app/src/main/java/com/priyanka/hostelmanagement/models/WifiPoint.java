package com.priyanka.hostelmanagement.models;

import java.util.ArrayList;
import java.util.List;

public class WifiPoint {
    private String pointId;
    private String location;
    private String block;
    private int floor;
    private String status;
    private double latitude;
    private double longitude;
    private List<String> reportedBy;

    public WifiPoint() {
        this.reportedBy = new ArrayList<>();
    }

    public WifiPoint(String pointId, String location, String block, int floor, String status) {
        this.pointId = pointId;
        this.location = location;
        this.block = block;
        this.floor = floor;
        this.status = status;
        this.reportedBy = new ArrayList<>();
    }

    public String getPointId() { return pointId; }
    public void setPointId(String pointId) { this.pointId = pointId; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getBlock() { return block; }
    public void setBlock(String block) { this.block = block; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public List<String> getReportedBy() { return reportedBy; }
    public void setReportedBy(List<String> reportedBy) { this.reportedBy = reportedBy; }
}