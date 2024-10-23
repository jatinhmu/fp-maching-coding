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

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
