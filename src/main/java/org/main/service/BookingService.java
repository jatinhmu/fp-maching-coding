package org.main.service;

import org.main.dto.OnBoardedSlot;
import org.main.dto.ShowTicket;
import org.main.utility.BaseUtil;

import java.util.*;

public class BookingService {
    ShowService showService;
    Map<String, List<ShowTicket>> bookedTicketsMap = new HashMap<>();
    Map<OnBoardedSlot, List<ShowTicket>> queuedTicketsMap = new HashMap<>();
    public BookingService(ShowService showService) {
        this.showService = showService;
    }

    public void bookTicket(String userName, String showName, Long showTime , int seats) {
        List<OnBoardedSlot> onBoardedSlots = showService.getOnBoardedSlots(showName);
        OnBoardedSlot onBoardedSlot = findSlowByShowTime(onBoardedSlots, showTime);
        if(onBoardedSlot == null) {
            System.out.println("Show not found for time " + showTime);
            return;
        }
        if(seats > onBoardedSlot.getCapacity()){
            List<ShowTicket> queuedTickets = queuedTicketsMap.get(onBoardedSlot);
            System.out.println("Seats exceeds capacity");

            return;
        }
        onBoardedSlot.setCapacity(onBoardedSlot.getCapacity() - seats);
        Long bookingId = BaseUtil.bookingIdGenerator();
        ShowTicket showTicket = new ShowTicket(bookingId, userName, showName, showName, seats);
        List<ShowTicket> bookedTickets = bookedTicketsMap.get(userName);
        if(bookedTickets == null) {
            bookedTickets = new ArrayList<>();
        }
        bookedTickets.add(showTicket);
        bookedTicketsMap.put(userName, bookedTickets);
        System.out.println("Booked. Booking id: " + bookingId);
    }

    private OnBoardedSlot findSlowByShowTime(List<OnBoardedSlot> onBoardedSlots, Long showTime) {
        for (OnBoardedSlot onBoardedSlot : onBoardedSlots) {
            if (Objects.equals(onBoardedSlot.getStartTime(), showTime)){
                return onBoardedSlot;
            }
        }
        return null;
    }
}
