package org.main.dto;

import org.main.enums.GENRE;

public class Show {
    private String showName;
    private GENRE genre;

    public Show(String showName, GENRE genre) {
        this.showName = showName;
        this.genre = genre;
    }

    public GENRE getGenre() {
        return genre;
    }

    public void setGenre(GENRE genre) {
        this.genre = genre;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }
}
