package com.crypto.CryptoHack.cr.controlar;


import com.crypto.CryptoHack.SellingDataStoreEntityImpl;
import com.crypto.CryptoHack.backjpa.SellingDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/getTodayData")
public class TodayDataController {

    Logger logger = LoggerFactory.getLogger(TodayDataController.class);
    @Autowired
    SellingDataStoreEntityImpl sellingDataStoreEntity;

    @GetMapping(path = "query",produces = MediaType.APPLICATION_JSON_VALUE,params = "today")
    public Resource<SellingDataStore> getTodaysSelledValues(@RequestParam(value = "today", required = false) String today){

        logger.debug("Get Count" +sellingDataStoreEntity.findAll());

        Optional<SellingDataStore> sellingDataStores = sellingDataStoreEntity.findAll().
                parallelStream()
                .sorted(Comparator.comparing(o -> ((SellingDataStore) o).getPrice()).reversed())
                .findFirst();

        Resource<SellingDataStore> listResource = new Resource<>(sellingDataStores.get());

        return listResource;
    }



}
