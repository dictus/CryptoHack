package com.crypto.CryptoHack.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

@JsonDeserialize(using = CryptoDeserializer.class)
public class CryptoDTO {

    private String timeSeris;

    private List<Instant> timeInterval;
    public List<SellingComponent> sellingComponents = new ArrayList<>();



    private LinkedHashMap<Date,SellingComponent> mycryptoDetails ;
    public LinkedHashMap<Date, SellingComponent> getMycryptoDetails() {
        if (this.mycryptoDetails == null)
            mycryptoDetails = new LinkedHashMap<>();
        return mycryptoDetails;
    }

    public void setMycryptoDetails(LinkedHashMap<Date, SellingComponent> mycryptoDetails) {
        this.mycryptoDetails = mycryptoDetails;
    }


    private Instant date;


    public String getTimeSeris() {
        return timeSeris;
    }

    public void setTimeSeris(String timeSeris) {
        this.timeSeris = timeSeris;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public List<SellingComponent> getSellingComponents() {
        return sellingComponents;
    }

    public void setSellingComponents(List<SellingComponent> sellingComponents) {
        this.sellingComponents = sellingComponents;
    }

    public static class CurrencyDetails{
        private String vaoluem;

        private String price;

        private String marketCap;

        public CurrencyDetails(String vaoluem, String price, String marketCap) {
            this.vaoluem = vaoluem;
            this.price = price;
            this.marketCap = marketCap;
        }

        public String getVaoluem() {
            return vaoluem;
        }

        public void setVaoluem(String vaoluem) {
            this.vaoluem = vaoluem;
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
}
