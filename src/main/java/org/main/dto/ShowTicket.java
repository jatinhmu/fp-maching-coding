package org.main.dto;

public class ShowTicket {
    private Long bookingId;
    private String userName;
    private String showName;
    private Long showTime;
    private int seats;

    public ShowTicket(Long bookingId, String userName, String showName, Long showTime, int seats) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.showName = showName;
        this.showTime = showTime;
        this.seats = seats;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Long getShowTime() {
        return showTime;
    }

    public void setShowTime(Long showTime) {
        this.showTime = showTime;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
}
