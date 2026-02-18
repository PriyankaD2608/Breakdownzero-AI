package com.priyanka.hostelmanagement.models;

import com.google.firebase.Timestamp;

public class Pass {
    private String passId;
    private String userId;
    private String userName;
    private String reason;
    private Timestamp outDate;
    private Timestamp returnDate;
    private String status;
    private String qrCode;
    private String approvedBy;
    private Timestamp createdAt;

    public Pass() {
    }

    public Pass(String passId, String userId, String userName, String reason,
                Timestamp outDate, Timestamp returnDate) {
        this.passId = passId;
        this.userId = userId;
        this.userName = userName;
        this.reason = reason;
        this.outDate = outDate;
        this.returnDate = returnDate;
        this.status = "pending";
        this.createdAt = Timestamp.now();
    }

    public String getPassId() {
        return passId;
    }

    public void setPassId(String passId) {
        this.passId = passId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getOutDate() {
        return outDate;
    }

    public void setOutDate(Timestamp outDate) {
        this.outDate = outDate;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getApprovedBy() {
        return approvedBy;
    }
}