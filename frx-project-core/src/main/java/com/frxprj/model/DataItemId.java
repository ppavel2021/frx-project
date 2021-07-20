package com.frxprj.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
public class DataItemId implements Serializable {

    private String ticker;
    private String date;
    private String time;

}
