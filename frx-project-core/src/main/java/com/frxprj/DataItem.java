package com.frxprj;

import javax.persistence.*;

@Entity
@IdClass(DataItemId.class)
@Table(name = "frx_data")
public class DataItem {

    @Id
    @Column(name = "ticker")
    private String ticker;

    @Column(name = "period")
    private Integer period;

    @Id
    @Column(name = "date")
    private String date;

    @Id
    @Column(name = "time")
    private String time;

    @Column(name = "open")
    private Double open;

    @Column(name = "high")
    private Double high;

    @Column(name = "low")
    private Double low;

    @Column(name = "close")
    private Double close;

    @Column(name = "vol")
    private Integer vol;

    public DataItem() {

    }

    public DataItem(String ticker, Integer period, String date, String time,
                    Double open, Double high, Double low, Double close, Integer vol) {
        this.ticker = ticker;
        this.period = period;
        this.date = date;
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.vol = vol;
    }
/*
    public DataItem(String date, String time,
                    Double open, Double high, Double low, Double close, Integer vol) {
        this.ticker = "";
        this.period = 0;
        this.date = date;
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.vol = vol;
    }
*/
    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Integer getVol() {
        return vol;
    }

    public void setVol(Integer vol) {
        this.vol = vol;
    }
}
