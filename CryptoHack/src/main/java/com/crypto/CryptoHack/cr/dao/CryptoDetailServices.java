package com.crypto.CryptoHack.cr.dao;

import com.crypto.CryptoHack.backjpa.domain.SellingDataStore;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface CryptoDetailServices {
    List<SellingDataStore> getAllCryptoDetails();

    Optional<SellingDataStore> getTodayHigestTransaction(Optional<Instant> date);
}
