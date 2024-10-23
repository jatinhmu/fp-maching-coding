package org.main;

import org.main.dto.OnBoardedSlot;
import org.main.enums.GENRE;
import org.main.service.BookingService;
import org.main.service.ShowService;


public class Main {
    public static void main(String[] args) {
        ShowService showService = new ShowService();
        showService.registerShow("TMKOC", GENRE.COMEDY);
//        showService.onboardSlot("TMKOC", 9L, 11L, 3);
        showService.onboardSlot("TMKOC", 9L, 10L, 3);
        showService.onboardSlot("TMKOC", 12L, 13L, 2);
        showService.onboardSlot("TMKOC", 15L, 16L, 5);

        showService.registerShow("The Sonu Nigam Live Event", GENRE.SINGING);
        showService.onboardSlot("The Sonu Nigam Live Event", 10L, 11L, 3);
        showService.onboardSlot("The Sonu Nigam Live Event", 13L, 14L, 2);
        showService.onboardSlot("The Sonu Nigam Live Event", 17L, 18L, 1);

        showService.getAvailableShowsByGenre(GENRE.COMEDY);

        BookingService bookingService = new BookingService(showService);
        bookingService.bookTicket("UserA", "TMKOC", 12L, 2);

        showService.getAvailableShowsByGenre(GENRE.COMEDY);

        bookingService.bookTicket("UserA", "TMKOC", 12L, 2);

        bookingService.cancelBooking(100L) ;

    }
}