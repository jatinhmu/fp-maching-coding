package org.main.dto;

public class OnBoardedSlot {
    private String showName;
    private Long startTime;
    private Long endTime;
    private int capacity;

    public OnBoardedSlot(String showName, Long startTime, Long endTime, int capacity) {
        this.showName = showName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }
}
