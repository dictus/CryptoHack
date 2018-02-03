package com.crypto.CryptoHack.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.time.Instant;


@JsonDeserialize(using = CommitDeserializer.class)
@SuppressWarnings("serial")
public class Currency implements Serializable{

   /* private String metaData;
    private String timeField;*/
    private Instant date;
    private CurrencyDetails currDeatils;

    public Currency(Instant date, CurrencyDetails currDeatils) {
        this.date = date;
        this.currDeatils = currDeatils;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public CurrencyDetails getCurrDeatils() {
        return currDeatils;
    }

    public void setCurrDeatils(CurrencyDetails currDeatils) {
        this.currDeatils = currDeatils;
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
