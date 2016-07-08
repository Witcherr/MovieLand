package com.potopalskyi.movieland.entity.dto;

public class TotalRatingDTO {
    private double sumRating;
    private int countRating;

    public double getSumRating() {
        return sumRating;
    }

    public void setSumRating(double sumRating) {
        this.sumRating = sumRating;
    }

    public int getCountRating() {
        return countRating;
    }

    public void setCountRating(int countRating) {
        this.countRating = countRating;
    }

    @Override
    public String toString() {
        return "TotalRatingDTO{" +
                "sumRating=" + sumRating +
                ", countRating=" + countRating +
                '}';
    }
}
