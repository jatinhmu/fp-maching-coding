package org.main.service;

import org.main.dto.OnBoardedSlot;
import org.main.dto.Show;
import org.main.enums.GENRE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowService {
    List<Show> registeredShows = new ArrayList<>();
    Map<GENRE, List<Show>> showsByGenre = new HashMap<>();
    Map<String, List<OnBoardedSlot>> onboardedSlotsMap = new HashMap<>();
    public void registerShow(String organizer, GENRE genre) {
        Show show = new Show(organizer, genre);
        registeredShows.add(show);
        showsByGenre.computeIfAbsent(genre, k -> new ArrayList<>()).add(show);
        System.out.println(organizer + "  show is registered !!");
    }

    public void onboardSlot(String showName, Long startTime, Long endTime, int cap) {
        if (!isValidTimeRange(startTime, endTime)){
            System.out.println("Sorry, show timings are of 1 hour only");
            return;
        }
        List<OnBoardedSlot> onBoardedSlots = onboardedSlotsMap.get(showName);
        if (onBoardedSlots == null) {
            onBoardedSlots = new ArrayList<>();
        }
        OnBoardedSlot onBoardedSlot = new OnBoardedSlot(showName, startTime, endTime, cap);
        onBoardedSlots.add(onBoardedSlot);
        onboardedSlotsMap.put(showName, onBoardedSlots);
        System.out.println("Done!");
    }

    private boolean isValidTimeRange(Long startTime, Long endTime) {
        return true;
    }

    private boolean isValidTimeSlot(List<OnBoardedSlot> onBoardedSlots, String timeRange) {
        return true;
    }

    public void getAvailableShowsByGenre(GENRE genre) {
        List<Show> shows = showsByGenre.get(genre);
        List<OnBoardedSlot> availableShowsByGenre = new ArrayList<>();
        for(Show show : shows){
            List<OnBoardedSlot> s = onboardedSlotsMap.get(show.getShowName());
            availableShowsByGenre.addAll(s);
        }

        for (OnBoardedSlot onBoardedSlot : availableShowsByGenre) {
            if(onBoardedSlot.getCapacity()>0)
                System.out.println(onBoardedSlot.getShowName() + ":" + onBoardedSlot.getStartTime() + ":" + onBoardedSlot.getEndTime() + " " + onBoardedSlot.getCapacity());
        }

    }

    public List<OnBoardedSlot> getOnBoardedSlots(String showName) {
        return onboardedSlotsMap.get(showName);
    }
}
