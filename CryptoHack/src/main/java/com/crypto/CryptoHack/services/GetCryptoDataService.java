package com.crypto.CryptoHack.services;

import com.crypto.CryptoHack.dto.Quote;
import com.crypto.CryptoHack.dto.TimeStampValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service("cryptoAPIClient2")
public class GetCryptoDataService implements GetCryptoAPIClient {


    final RestTemplate restTemplate;

   public GetCryptoDataService(RestTemplateBuilder restTemplateBuilder ) {
        this.restTemplate = restTemplateBuilder.build();
    }
   final String url = "https://gturnquist-quoters.cfapps.io/api/random";


    @Override
    public Quote getLiveQuote(){
        Quote quote = this.restTemplate.getForObject(url,Quote.class);
        return quote;
    }
    private RequestEntity<?> createRequestEntity(String url) {
        try {
            return RequestEntity.get(new URI(url))
                    .accept(MediaType.APPLICATION_JSON).build();
        }
        catch (URISyntaxException ex) {
            throw new IllegalStateException("Invalid URL " + url, ex);
        }
    }
}
