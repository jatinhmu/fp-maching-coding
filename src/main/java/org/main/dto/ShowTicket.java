package org.main.dto;

public class ShowTicket {
    private Long bookingId;
    private String userName;
    private String showName;
    private String startTime;
    private int seats;

    public ShowTicket(Long bookingId, String userName, String showName, String showName1, int seats) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.showName = showName;
        this.startTime = showName1;
        this.seats = seats;
    }
}
