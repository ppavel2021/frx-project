package com.frxprj;

import java.io.Serializable;

public class DataItemId implements Serializable {

    private String ticker;
    private String date;
    private String time;

    public DataItemId(String ticker, String date, String time) {
        this.ticker = ticker;
        this.date = date;
        this.time = time;
    }

    public DataItemId() {

    }
}
