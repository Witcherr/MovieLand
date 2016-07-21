package com.potopalskyi.movieland.entity.dto;

public class CurrencyDTO {

    private double rate;
    private String cc;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }
}
