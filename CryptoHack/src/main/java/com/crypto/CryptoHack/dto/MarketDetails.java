package com.crypto.CryptoHack.dto;


import java.time.Instant;

public class MarketDetails {
    String marketCurrency;
    String marketName;
    Instant marketLastUpdate;
    String timeZone;

    public MarketDetails(String marketCurrency, String marketName, Instant marketLastUpdate, String timeZone) {
        this.marketCurrency = marketCurrency;
        this.marketName = marketName;
        this.marketLastUpdate = marketLastUpdate;
        this.timeZone = timeZone;
    }

    public String getMarketCurrency() {
        return marketCurrency;
    }

    public void setMarketCurrency(String marketCurrency) {
        this.marketCurrency = marketCurrency;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Instant getMarketLastUpdate() {
        return marketLastUpdate;
    }

    public void setMarketLastUpdate(Instant marketLastUpdate) {
        this.marketLastUpdate = marketLastUpdate;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
