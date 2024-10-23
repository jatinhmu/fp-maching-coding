package org.main.utility;

public class BaseUtil {

    private static Long bookingId = 100L;
    public static Long bookingIdGenerator(){
        return bookingId++;
    }
}
