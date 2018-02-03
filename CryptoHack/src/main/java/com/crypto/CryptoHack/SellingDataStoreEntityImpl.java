package com.crypto.CryptoHack;

import com.crypto.CryptoHack.backjpa.SellingDataStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellingDataStoreEntityImpl extends JpaRepository<SellingDataStore,Long>{

    List<SellingDataStore> findAll();

    List<SellingDataStore> findAllBy(Pageable pageable);
    //void insertDataToTable();


    @Override
    Page<SellingDataStore> findAll(Pageable pageable);
}
