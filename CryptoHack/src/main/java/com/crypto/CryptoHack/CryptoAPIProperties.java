package com.crypto.CryptoHack;

import org.springframework.boot.context.properties.ConfigurationProperties;

public class CryptoAPIProperties {

    private final Alphavantage github = new Alphavantage();

    public Alphavantage getGithub() {
        return github;
    }

    public static class Alphavantage {

        /**
         * Access token ("username:access_token") to query public github endpoints.
         */
        private String apikey;

        public String getApikey() {
            return apikey;
        }

        public void setApikey(String apikey) {
            this.apikey = apikey;
        }
    }

}
