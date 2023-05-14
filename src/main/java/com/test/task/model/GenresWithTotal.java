package com.test.task.model;

public class GenresWithTotal {

    String numVotes;
    String primaryTitle;
    int Total;
    String genres;

    public String getNumVotes() {
        return numVotes;
    }

    public void setNumVotes(String numVotes) {
        this.numVotes = numVotes;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public void setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
