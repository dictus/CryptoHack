package com.crypto.CryptoHack.backjpa.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "SELLING_DATA_STORE")
public class SellingDataStore
        implements Serializable {


    private static final long serialVersionUID = -3327846851216096235L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    private String volume;

    private String price;


    private String marketCap;

    @Temporal(TemporalType.TIMESTAMP)
    private Date toDay;

    public SellingDataStore(int id,String vaoluem, String price, String marketCap,Date date) {
        this.id = id;
        this.volume = vaoluem;
        this.price = price;
        this.marketCap = marketCap;
        this.toDay=date;
    }

    public SellingDataStore() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getToDay() {
        return toDay;
    }

    public void setToDay(Date toDay) {
        this.toDay = toDay;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(String marketCap) {
        this.marketCap = marketCap;
    }
}
