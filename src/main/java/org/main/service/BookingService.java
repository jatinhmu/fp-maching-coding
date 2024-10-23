package org.main.service;

import org.main.dto.OnBoardedSlot;
import org.main.dto.Show;
import org.main.dto.ShowTicket;
import org.main.utility.BaseUtil;

import java.util.*;

public class BookingService {
    ShowService showService;
    Map<String, List<ShowTicket>> userTobookedTicketsMap = new HashMap<>();
    Map<Long, ShowTicket> bookingIdTobookedTicketMap = new HashMap<>();
    Map<OnBoardedSlot, Queue<ShowTicket>> queuedTicketsMap = new HashMap<>();
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

        // create ticket with booking id
        Long bookingId = BaseUtil.bookingIdGenerator();
        ShowTicket showTicket = new ShowTicket(bookingId, userName, showName, showTime, seats);

        // check whether to put in waiting list queue or not
        if(seats > onBoardedSlot.getCapacity()){
            Queue<ShowTicket> queuedTickets = queuedTicketsMap.get(onBoardedSlot);
            if(queuedTickets == null) {
                queuedTickets = new LinkedList<>();
            }
            queuedTickets.add(showTicket);
            queuedTicketsMap.put(onBoardedSlot, queuedTickets);
            System.out.println("Booking Id : " + bookingId + " , Wait listing");
            return;
        }
        //reduce the capacity and book the ticket
        onBoardedSlot.setCapacity(onBoardedSlot.getCapacity() - seats);
        List<ShowTicket> bookedTickets = userTobookedTicketsMap.get(userName);
        if(bookedTickets == null) {
            bookedTickets = new ArrayList<>();
        }
        bookedTickets.add(showTicket);
        userTobookedTicketsMap.put(userName, bookedTickets);
        bookingIdTobookedTicketMap.put(bookingId, showTicket);
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

    public void cancelBooking(Long bookingId) {
        ShowTicket showTicket = bookingIdTobookedTicketMap.remove(bookingId);
        String userName = showTicket.getUserName();
        List<ShowTicket> bookedTickets = userTobookedTicketsMap.get(userName);
        bookedTickets.remove(showTicket);
        userTobookedTicketsMap.put(userName, bookedTickets);

        // update the capacity
        List<OnBoardedSlot> onBoardedSlots = showService.getOnBoardedSlots(showTicket.getShowName());
        OnBoardedSlot onBoardedSlot = findSlowByShowTime(onBoardedSlots, showTicket.getShowTime());
        onBoardedSlot.setCapacity(onBoardedSlot.getCapacity() + showTicket.getSeats());
        Queue<ShowTicket> queuedTickets = queuedTicketsMap.get(onBoardedSlot);
        ShowTicket show = queuedTickets.peek();
        if(show.getSeats()<=onBoardedSlot.getCapacity()){
            queuedTickets.remove();
            userTobookedTicketsMap.put(userName, bookedTickets);
            bookingIdTobookedTicketMap.put(bookingId, showTicket);
        }
    }
}
