package com.priyanka.hostelmanagement.models;

public class WaterSlot {
    private String slotId;
    private String season;
    private int floor;
    private int coolerNumber;
    private String startTime;
    private String endTime;

    public WaterSlot() {}

    public WaterSlot(String slotId, String season, int floor, int coolerNumber,
                     String startTime, String endTime) {
        this.slotId = slotId;
        this.season = season;
        this.floor = floor;
        this.coolerNumber = coolerNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getSlotId() { return slotId; }
    public void setSlotId(String slotId) { this.slotId = slotId; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }

    public int getCoolerNumber() { return coolerNumber; }
    public void setCoolerNumber(int coolerNumber) { this.coolerNumber = coolerNumber; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
}