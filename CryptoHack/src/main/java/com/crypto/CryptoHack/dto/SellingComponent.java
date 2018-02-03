package com.crypto.CryptoHack.dto;

import java.util.Date;

public class SellingComponent {
        private String vaoluem;

        private String price;

        private String marketCap;

        private Date toDay;

        public SellingComponent(String vaoluem, String price, String marketCap,Date date) {
            this.vaoluem = vaoluem;
            this.price = price;
            this.marketCap = marketCap;
            this.toDay=date;
        }

    public Date getToDay() {
        return toDay;
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
