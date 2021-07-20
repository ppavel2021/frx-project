package com.frxprj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

}
