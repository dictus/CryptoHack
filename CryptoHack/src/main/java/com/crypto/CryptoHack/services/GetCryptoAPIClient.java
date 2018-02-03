package com.crypto.CryptoHack.services;

import com.crypto.CryptoHack.dto.Quote;

public interface GetCryptoAPIClient {
    Quote getLiveQuote();

}
